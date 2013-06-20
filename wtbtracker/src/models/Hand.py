'''
Created on 14 Aug 2009

@author: wtb
'''

import re, constants

'''
A class which would represent individual hands, it keeps track on position in the hand and stores data on the hand
'''         
class Hand():
    
    # Dictionary which defines hand stages and there associated regular expressions
    KHANDSTAGE_DICT     = dict( {
                                      'newhand' : dict( { 'regex' : "Full Tilt Poker Game.*",       'stage' : 0 } ),
                                      'preflop' : dict( { 'regex' : "\*\*\*\sHOLE\sCARDS\s\*\*\*.*",'stage' : 1 } ),
                                      'flop'    : dict( { 'regex' : "\*\*\*\sFLOP\s\*\*\*.*",       'stage' : 2 } ),
                                      'turn'    : dict( { 'regex' : "\*\*\*\sTURN\s\*\*\*.*",       'stage' : 3 } ),
                                      'river'   : dict( { 'regex' : "\*\*\*\sRIVER\s\*\*\*.*",      'stage' : 4 } ),
                                      'showdown': dict( { 'regex' : "\*\*\*\sSHOW DOWN\s\*\*\*.*",  'stage' : 5 } ),
                                      'summary' : dict( { 'regex' : "\*\*\*\sSUMMARY\s\*\*\*.*",    'stage' : -1 } ),
                               } )


    # Dictionary which defines hand stages and there associated regular expressions
    KACTIONSTAGES_DICT    = dict( {  
                                      'preflop' : 1,
                                      'flop'    : 2,    
                                      'turn'    : 3, 
                                      'river'   : 4 
                               } )

    
    # Function which stores a dictionary of people and there action count
    iPlayersActionCountDict   = dict() 

    # Integer denoting the stage of the hand 
    iHandStage                 = -1
    
    # Players associated to the hand
    iPlayersInHand             = dict()
             
    # Function will be passed a line and will use the defined regular expressions to deduce what is happening on the line and store the data
    # appropriately
    def processActionsText( self, aline ):
        try:
            # Find player associated to the action
            playerAction = re.search( constants.KREGEX_ACTION, aline )
            # Get all the possible actions
            actions = constants.KACTION_DICT.keys()
            # Initialise the count for each action for the player if not already initialized 
            if( playerAction.group( 1 ) not in self.iPlayersActionCountDict ):
                # Initialise player
                self.iPlayersActionCountDict[ playerAction.group( 1 ) ] = dict()
                for actionStage in range( 1, 5 ):
                    # Initialise each stage of the hand for each player
                    self.iPlayersActionCountDict[ playerAction.group( 1 ) ][ actionStage ] = dict()
                    # Initialise each action so it can be incremented
                    for action in actions:
                        # Initialise each action of each hand stage of each player
                        self.iPlayersActionCountDict[ playerAction.group( 1 ) ][ actionStage ][ action ] = 0
            # Increment action 
            self.iPlayersActionCountDict[ playerAction.group( 1 ) ][ self.iHandStage ][ playerAction.group( 2 ) ]  += 1 
            # Now try match the actions in the line
        except AttributeError:
            print( "Not in the correct line to be able to process a player and action" )

    # Function to set the stage of the hand 
    def setHandStage( self, aline ):
        # Cycle through hand stages and set the correct state when found
        handStages = self.KHANDSTAGE_DICT.keys()
        for handStage in handStages:
            # Now match try match the hand stage in the line
            if( re.match( self.KHANDSTAGE_DICT[ handStage ][ 'regex' ], aline ) ):
                self.iHandStage = self.KHANDSTAGE_DICT[ handStage ][ 'stage' ] 
                break    
        if( constants.debugFlag == True ):
            print( "handstage " + str( self.iHandStage ) )

    # Function which process who the subject person is and associates action statistics to him
    def processPlayer( self, aline ):
        try:
            # Firstly extract the name from the line    
            player = re.search( constants.KREGEX_ACTION, aline )
            # Insert in playersActionDict to build up list of the players
            self.iPlayersActionDict[ player.group( 1 ) ] = 0
        except AttributeError:
            print( "Not in the correct line to be able to process a player" )            
            
    
        
