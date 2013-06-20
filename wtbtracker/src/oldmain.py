'''
Created on 14 Aug 2009

@author: johnbower
'''
import os, re

KSPACE                = " " 
KPATH                       = "../testdata/"
KREGEX_ROGERMUSHROOM        = ".*rogermushroom.*" 
KREGEX_ROGERMUSHROOM_ACTION = "rogermushroom\s(folds|calls|raises|checks).*" 
KREGEX_NEW_HAND                = "Full Tilt Poker.*" 
KREGEX_HOLE_CARDS        = ".*HOLE CARDS*"
KREGEX_FLOP            = ".*FLOP*"
KREGEX_TURN            = ".*TURN*"
KREGEX_RIVER                = ".*RIVER*"

KRESULTS_FILE_NAME          = "results.txt"

KFOLD_ACTION                = "folds"
KCALL_ACTION                = "calls"
KRAISE_ACTION                = "raises"
KCHECK_ACTION                = "checks"

KPREFLOP_FOLD_ACTION        = "preflop folds"
KPREFLOP_CALL_ACTION        = "preflop calls"
KPREFLOP_RAISE_ACTION        = "preflop raises"
KPREFLOP_CHECK_ACTION        = "preflop checks"
KFLOP_FOLD_ACTION        = "flop folds"
KFLOP_CALL_ACTION        = "flop calls"
KFLOP_RAISE_ACTION        = "flop raises"
KFLOP_CHECK_ACTION        = "flop checks"
KTURN_FOLD_ACTION        = "turn folds"
KTURN_CALL_ACTION        = "turn calls"
KTURN_RAISE_ACTION        = "turn raises"
KTURN_CHECK_ACTION        = "turn checks"
KRIVER_FOLD_ACTION        = "river folds"
KRIVER_CALL_ACTION        = "river calls"
KRIVER_RAISE_ACTION        = "river raises"
KRIVER_CHECK_ACTION        = "river checks"

KNEW_HAND                = "Full Tilt Poker"

handStage            = 0

KSTAGE_HOLE_CARDS        = 0
KSTAGE_FLOP            = 1 
KSTAGE_TURN            = 2 
KSTAGE_RIVER                = 3 

foldCount                = 0 
callCount                = 0
raiseCount                = 0
checkCount             = 0

preFlopFoldCount        = 0
preFlopCallCount            = 0 
preFlopRaiseCount        = 0
preFlopCheckCount        = 0

flopFoldCount                = 0
flopCallCount                = 0
flopRaiseCount                = 0
flopCheckCount            = 0 

turnFoldCount                = 0
turnCallCount                = 0
turnRaiseCount                = 0
turnCheckCount            = 0

riverFoldCount                = 0
riverCallCount                = 0
riverRaiseCount            = 0
riverCheckCount            = 0

handCount            = 0    

results                     = open( KRESULTS_FILE_NAME, "w" )

logFiles = os.listdir( KPATH )

for logFile in logFiles:
    file = os.path.join( KPATH, logFile )
    text = open( file, "r" )

    for line in text:
        if re.match( KREGEX_NEW_HAND, line ):
            handCount = handCount + 1
            handStage = KSTAGE_HOLE_CARDS
        elif re.match( KREGEX_FLOP, line ):
            handStage = KSTAGE_FLOP
        elif re.match( KREGEX_TURN, line ):
            handStage = KSTAGE_TURN        
        elif re.match( KREGEX_RIVER, line ):
            handStage = KSTAGE_RIVER

        elif re.match( KREGEX_ROGERMUSHROOM_ACTION, line ):
            action = re.search( KREGEX_ROGERMUSHROOM_ACTION, line )
            if( action != None ): 
                if( handStage == KSTAGE_HOLE_CARDS ):
                    if( action.group( 1 ) == KFOLD_ACTION ): 
                        preFlopFoldCount = preFlopFoldCount + 1
                    elif( action.group( 1 ) == KCALL_ACTION ): 
                        preFlopCallCount = preFlopCallCount + 1
                    elif( action.group( 1 ) == KRAISE_ACTION ): 
                        preFlopRaiseCount = preFlopRaiseCount + 1
                    elif( action.group( 1 ) == KCHECK_ACTION ): 
                        preFlopCheckCount = preFlopCheckCount + 1
                elif( handStage == KSTAGE_FLOP ):
                    if( action.group( 1 ) == KFOLD_ACTION ): 
                        flopFoldCount = flopFoldCount + 1
                    elif( action.group( 1 ) == KCALL_ACTION ): 
                        flopCallCount = flopCallCount + 1
                    elif( action.group( 1 ) == KRAISE_ACTION ): 
                        flopRaiseCount = flopRaiseCount + 1
                    elif( action.group( 1 ) == KCHECK_ACTION ): 
                        flopCheckCount = flopCheckCount + 1
                elif( handStage == KSTAGE_TURN ):
                    if( action.group( 1 ) == KFOLD_ACTION ): 
                        turnFoldCount = turnFoldCount + 1
                    elif( action.group( 1 ) == KCALL_ACTION ): 
                        turnCallCount = turnCallCount + 1
                    elif( action.group( 1 ) == KRAISE_ACTION ): 
                        turnRaiseCount = turnRaiseCount + 1
                    elif( action.group( 1 ) == KCHECK_ACTION ): 
                        turnCheckCount = turnCheckCount + 1
                elif( handStage == KSTAGE_RIVER ):
                    if( action.group( 1 ) == KFOLD_ACTION ): 
                        riverFoldCount = riverFoldCount + 1
                    elif( action.group( 1 ) == KCALL_ACTION ): 
                        riverCallCount = riverCallCount + 1
                    elif( action.group( 1 ) == KRAISE_ACTION ): 
                        riverRaiseCount = riverRaiseCount + 1
                    elif( action.group( 1 ) == KCHECK_ACTION ): 
                        riverCheckCount = riverCheckCount + 1
                                    
        if re.match( KREGEX_ROGERMUSHROOM_ACTION, line ):
            action = re.search( KREGEX_ROGERMUSHROOM_ACTION, line )
            if( action != None ): 
                if( action.group( 1 ) == KFOLD_ACTION ): 
                    foldCount = foldCount + 1
                elif( action.group( 1 ) == KCALL_ACTION ): 
                    callCount = callCount + 1
                elif( action.group( 1 ) == KRAISE_ACTION ): 
                    raiseCount = raiseCount + 1
                elif( action.group( 1 ) == KCHECK_ACTION ): 
                    checkCount = checkCount + 1

print()

print( KNEW_HAND + KSPACE + str( handCount ) )

print()

print( KFOLD_ACTION + KSPACE + str( foldCount ) )
print( KCALL_ACTION + KSPACE + str( callCount ) )
print( KRAISE_ACTION + KSPACE + str( raiseCount ) ) 
print( KCHECK_ACTION + KSPACE + str( checkCount ) ) 

print()

print( KFOLD_ACTION + KSPACE + str( foldCount / ( callCount + foldCount + raiseCount + checkCount ) ) )
print( KCALL_ACTION + KSPACE + str( callCount / ( callCount + foldCount + raiseCount + checkCount ) ) )
print( KRAISE_ACTION + KSPACE + str( raiseCount / ( callCount + foldCount + raiseCount + checkCount ) ) )
print( KCHECK_ACTION + KSPACE + str( checkCount / ( callCount + foldCount + raiseCount + checkCount ) ) )

print()

print( KPREFLOP_FOLD_ACTION + KSPACE + str( preFlopFoldCount ) )
print( KPREFLOP_CALL_ACTION + KSPACE + str( preFlopCallCount ) )
print( KPREFLOP_RAISE_ACTION + KSPACE + str( preFlopRaiseCount ) ) 
print( KPREFLOP_CHECK_ACTION + KSPACE + str( preFlopCheckCount ) ) 

print()

print( KPREFLOP_FOLD_ACTION + KSPACE + str( preFlopFoldCount / ( preFlopCallCount + preFlopFoldCount + preFlopRaiseCount + preFlopCheckCount ) ) ) 
print( KPREFLOP_CALL_ACTION + KSPACE + str( preFlopCallCount / ( preFlopCallCount + preFlopFoldCount + preFlopRaiseCount + preFlopCheckCount ) ) )
print( KPREFLOP_RAISE_ACTION + KSPACE + str( preFlopRaiseCount / ( preFlopCallCount + preFlopFoldCount + preFlopRaiseCount + preFlopCheckCount ) ) )
print( KPREFLOP_CHECK_ACTION + KSPACE + str( preFlopCheckCount / ( preFlopCallCount + preFlopFoldCount + preFlopRaiseCount + preFlopCheckCount ) ) )

print()

print( KFLOP_FOLD_ACTION + KSPACE + str( flopFoldCount ) )
print( KFLOP_CALL_ACTION + KSPACE + str( flopCallCount ) )
print( KFLOP_RAISE_ACTION + KSPACE + str( flopRaiseCount ) ) 
print( KFLOP_CHECK_ACTION + KSPACE + str( flopCheckCount ) ) 

print()

print( KFLOP_FOLD_ACTION + KSPACE + str( flopFoldCount / ( flopFoldCount + flopCheckCount + flopRaiseCount + flopCallCount ) ) )
print( KFLOP_CALL_ACTION + KSPACE + str( flopCallCount / ( flopFoldCount + flopCheckCount + flopRaiseCount + flopCallCount ) ) )
print( KFLOP_RAISE_ACTION + KSPACE + str( flopRaiseCount / ( flopFoldCount + flopCheckCount + flopRaiseCount + flopCallCount ) ) ) 
print( KFLOP_CHECK_ACTION + KSPACE + str( flopCheckCount / ( flopFoldCount + flopCheckCount + flopRaiseCount + flopCallCount ) ) ) 

print()

print( KTURN_FOLD_ACTION + KSPACE + str( turnFoldCount ) )
print( KTURN_CALL_ACTION + KSPACE + str( turnCallCount ) )
print( KTURN_RAISE_ACTION + KSPACE + str( turnRaiseCount ) ) 
print( KTURN_CHECK_ACTION + KSPACE + str( turnCheckCount ) ) 

print()

print( KTURN_FOLD_ACTION + KSPACE + str( turnFoldCount / ( turnFoldCount + turnCheckCount + turnRaiseCount + turnCallCount ) ) )
print( KTURN_CALL_ACTION + KSPACE + str( turnCallCount / ( turnFoldCount + turnCheckCount + turnRaiseCount + turnCallCount ) ) )
print( KTURN_RAISE_ACTION + KSPACE + str( turnRaiseCount / ( turnFoldCount + turnCheckCount + turnRaiseCount + turnCallCount ) ) ) 
print( KTURN_CHECK_ACTION + KSPACE + str( turnCheckCount / ( turnFoldCount + turnCheckCount + turnRaiseCount + turnCallCount ) ) ) 

print()

print( KRIVER_FOLD_ACTION + KSPACE + str( riverFoldCount ) )
print( KRIVER_CALL_ACTION + KSPACE + str( riverCallCount ) )
print( KRIVER_RAISE_ACTION + KSPACE + str( riverRaiseCount ) ) 
print( KRIVER_CHECK_ACTION + KSPACE + str( riverCheckCount ) ) 

print()

print( KRIVER_FOLD_ACTION + KSPACE + str( riverFoldCount / ( riverFoldCount + riverCheckCount + riverRaiseCount + riverCallCount ) ) )
print( KRIVER_CALL_ACTION + KSPACE + str( riverCallCount / ( riverFoldCount + riverCheckCount + riverRaiseCount + riverCallCount ) ) )
print( KRIVER_RAISE_ACTION + KSPACE + str( riverRaiseCount / ( riverFoldCount + riverCheckCount + riverRaiseCount + riverCallCount ) ) ) 
print( KRIVER_CHECK_ACTION + KSPACE + str( riverCheckCount / ( riverFoldCount + riverCheckCount + riverRaiseCount + riverCallCount ) ) ) 

print()

print( KFOLD_ACTION + KSPACE + str( foldCount ) )
print( KCALL_ACTION + KSPACE + str( callCount ) )
print( KRAISE_ACTION + KSPACE + str( raiseCount ) )
print( KCHECK_ACTION + KSPACE + str( checkCount ) )





