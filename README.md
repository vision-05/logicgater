# logicgater
Customisable logic gate sim, with one fundamental part: the nand gate

Specification for data:
Gates represented the easiest way - unevaluated clojure code. The only available functions are ones that already exist in our table, that can be read from a file, or defaults to only containing the following nand function

(def core/nand-hidden (fn [a b] (bit-and-not a b)))

This makes program minimal and less error prone, as clojure evaluator handles the heavy lifting. I will have to work out a way for the GUI to write the clojure code, and most importantly to construct wires as they are not stored

For GUI to clojure:
    Each input block will be treated as a parameter
    Output block will be return value
    Each gate has input nodes and exit node
    Wire connecting to input node will put everything connected from the wire into the parameter of the gate
    Wire connecting from output node will not need to be considered, so we evaluate whole diagram from left to right

    We will have one function to compile a gate to clojure code.

Input block is changeable by user
(def core/input-block {:state 0}) ;consider making these one def, these might have to be mutable
(def core/output-block {:state 0})
Output block is attached to a gate

Think about ways for using this immutably, although having it atomic is not a bad compromise

The gates are stored in a map:
{:nand [nil core/nand]
 :not ['(fn [a] (:nand a a)) (fn [a] (core/nand a a))]}

The first element of the vector is an unevaluated S-expression, the second is a corresponding function
As gates are created from just data, or read in, at the same time a function produces the code equivalent
All we need is one function to produce a truth table when we want to evaluate the results
The truth table function works on the code element of the vector, while we can read/write the data version
This reduces amount of processing needed to convert between one format and the other, although the memory needed is greater
We can use this format for smart deletion, where deleting one gate will also delete all other gates using it, as only gates further down the list can depend on it, and we can read the data side to compare the keywords to confirm this

I can also experiment with another version that only uses a map with one type, either data or code and compare performmance/memory statistics, once functionality is up and running

Later on, experiment with a design based on agents

This will be exposed to user as drag and drop blocks with wire connections between them. Connection from output to input will put previous function inside input. Think about how to reuse outputs, we can bind in a let

The GUI input blocks with on and off switch will at first just be used to find corresonding inputs for the truth table, later add functionality to light up the cables
GUI will need to figure out how to draw diagrams from the data/interpret from data into GUI form

GUI will create gate on completion, however incomplete gates will need to be saved too

Heirarchy:
    each screen is a new gate, composed of other previously built gates
    gates can be imported and selected from menu
    gates are stored in edn file
    figure way to convert function - function is unevaluated clojure S-expr with keywords instead of function names
        all I have to do is write code to replace the keywords when running and the clojure evaluator does the rest of the checking (maybe I should test a couple things if I have motivation)

Steps:
    Backend - work out data structure DONE
              convert between data structure and code IN PROGRESS
              loading and saving data to and from files
              create GUI
              link up GUI objects to data structure

Big picture:
    GUI drag and drop blocks (with HumbleUI)
    Nand block already defined
    User defines blocks
    User provides or creates symbols
    Library of common symbols also provided
    User can import blocks in file

    Editor layout: tabbed editor, tree updated when file saved
    Think about how to deal with dependent files, version control/undo tree etc

Testing:
    create-gates needs to check:
        arity of function created matches the no of args in the list
        function returns correct number of arguments (either in vector or as single return value)
        invalid list spits out error
        test generated function behaves as expected with truth table for various gates/circuits
    truth-table needs to check:
        correct numbers generated for truth table
        only binary values are generated
