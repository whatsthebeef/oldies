#!/usr/bin python 

import re, os, constants, sys, codecs 
from models.Table import Table
from models.Hand import Hand
import __main__


# Position of the parser
inSeatingSection = False

# Overall count action dictionary for all players parsed
totalPlayerActionCountDict = dict() 

# Overall percentage action dictionary for all players parsed
totalPlayerActionPercentageDict = dict() 

# Function which takes a dictionary object containing action and associated count for each hand and 
# adds to a total dictionary count use of all hands
def setTotalPlayerActionCountDict( aPlayersActionCountDict ):
	global totalPlayerActionCountDict
	for player in set( aPlayersActionCountDict ):
		if( player not in totalPlayerActionCountDict ):
			totalPlayerActionCountDict[ player ] = dict()
			for actionStage in range( 1, 5 ):
				totalPlayerActionCountDict[ player ][ actionStage ] = dict( ( action, 0 ) for action in constants.KACTION_DICT.keys() )
		for actionStage in range( 1, 5 ):
			for action in constants.KACTION_DICT.keys():
				totalPlayerActionCountDict[ player ][ actionStage ][ action ] += aPlayersActionCountDict[ player ][ actionStage ][ action ] 
	
# Function which takes a dictionary object containing action and associated count and calculates percentage use of action
def percentageCalcPlayersInHand( aPlayersInHand ):
	# Dictionary for percentage actions of players in hand
	handPlayerActionPercentage = dict()
	global totalPlayerActionCountDict
	for player in set( aPlayersInHand ):
		if( player not in handPlayerActionPercentage ):
			handPlayerActionPercentage[ player ] = dict()
			for actionStage in range( 1, 5 ):
				handPlayerActionPercentage[ player ][ actionStage ] = dict( ( action, 0 ) for action in constants.KACTION_DICT.keys() )
		for actionStage in range( 1, 5 ):
			totalCount = 0
			for action in constants.KACTION_DICT.keys(): 
				try:
					totalCount += totalPlayerActionCountDict[ player ][ actionStage ][ action ]
				except KeyError:
					if( constants.debugFlag == True ):
						print( "Player sat down but hasn't acted" )
					break
			for action in constants.KACTION_DICT.keys():
				try:
					if( totalCount != 0 ):
						handPlayerActionPercentage[ player ][ actionStage ][ action ] = round( ( 
								totalPlayerActionCountDict[ player ][ actionStage ][ action ] / totalCount ), 2 )
				except KeyError:
					if( constants.debugFlag == True ):
						print( "Player sat down but hasn't acted" )
					break
	return handPlayerActionPercentage  
	
# A function to define whether we are in the seating section
def setInSeatingSection( aline, hand ):
	# If its a 'seat' line set inSeatingSection to true 
	if((re.match(constants.KREGEX_SEAT, aline)) and (hand.iHandStage == 0)):
		inSeatingSection = True
	else:
		inSeatingSection = False
		
def main():
	# The Poker table the hand is taking place
	table = Table()
	hand = Hand()
	
	# Get all files in log file location
	logFiles = os.listdir(constants.KPATH)

	# Open files ready to feed to the parser
	for logFile in logFiles:
		file = os.path.join(constants.KPATH, logFile)
		text = codecs.open( file, 'r', 'utf-16', 'ignore' )

		for line in text:
			# Find the table information, players
			if( ( re.match( constants.KREGEX_SEAT, line ) ) and ( hand.iHandStage == 0 ) ):
				table.getPlayersAtTable( line )
			# If we are passed the seating find the hand stage information
			elif( re.match( constants.KREGEX_HAND_STAGE, line ) 
						or re.match( constants.KREGEX_START_STAGE, line ) ):
				hand.setHandStage(line)
				# If we are in a pre-flop stage then set then set the players in the hand
				if(hand.iHandStage == hand.KHANDSTAGE_DICT[ 'preflop' ][ 'stage' ]):
					hand.iPlayersInHand = table.iPlayersCashDict
				# IF we are in summary stage then calculate the percentage and add to global statistics
				elif( hand.iHandStage == hand.KHANDSTAGE_DICT[ 'summary' ][ 'stage' ]):
					setTotalPlayerActionCountDict( hand.iPlayersActionCountDict )
					hand.iPlayersActionCountDict = dict()
			# If not the table info or hand stage start processing player actions
			elif( re.match( constants.KREGEX_ACTION, line ) ):
				hand.processActionsText( line )
	
	for player in set( percentageCalcPlayersInHand( hand.iPlayersInHand ) ):
		print( "\n" + player )
		for actionStage in range( 1, 5 ):
			print( "\n" + str( actionStage ) ) 
			for action in constants.KACTION_DICT.keys():
				print( "       " + action + " " + str( percentageCalcPlayersInHand( set( 
			   			hand.iPlayersInHand ) )[ player][ actionStage ][ action ] ) )
		
if __name__ == '__main__':
	main()
	
		
	


