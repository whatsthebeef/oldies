'''
Created on 14 Aug 2009

@author: johnbower
'''

# Include debug statements
debugFlag                   = False

# Some action
KREGEX_ACTION               = "(.*)\s(folds|calls|raises|checks).*"

# Path to find log files
KPATH                       = "../testdata/"

# Regex to find the Seat declaration in the hands
KREGEX_SEAT                 = "Seat\s\d:\s(.*)\s\(\$(.*)\).*"

# Regex to find the hand stage
KREGEX_HAND_STAGE           = "\*\*\*\s(.*)\s\*\*\*.*"

# Regex to find the start hand stage
KREGEX_START_STAGE          = "Full Tilt Poker Game.*"

# Dictionary stores strings of interest and there associated regular expressions required to detect them 
KACTION_DICT                = dict( { 
                                     'calls'   : ".*\scalls\s.*", 
                                     'folds'   : ".*\sfolds\s.*", 
                                     'raises'  : ".*\sraises\s.*", 
                                     'checks'  : ".*\schecks\s.*" 
                                   } )
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
