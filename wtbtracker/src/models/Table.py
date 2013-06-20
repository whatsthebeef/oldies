'''
Created on 14 Aug 2009

@author: johnbower
'''

import re, constants

'''
A class which represents the poker table, including players and cash at the table, position of the button
'''
class Table():
    
    # Dictionary stores the players at the table their position on the table and their stacks
    iPlayersCashDict   = dict()
    
    # Integer storing the position of the button
    iButtonPosition    = 1
    
    # A Function to build up the dictionary of players in a hand, handPlayersActionDict
    def getPlayersAtTable( self, aline ):
        # Find the first "Seat" string in the hand and set add name to dict
        try:
            player = re.search( constants.KREGEX_SEAT, aline )
            self.iPlayersCashDict[ player.group( 1 ) ] = player.group( 2 )
        except AttributeError:
            print( "Not in the correct line to be able to process players at table" )

    
                    

        