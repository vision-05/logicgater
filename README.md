# logicgater
Customisable logic gate sim, with one fundamental part: the nand gate

Specification for data:

Logic gates:
    Stored in a map, name and corresponding function in data form.
    We need function to translate between data and code, parse the map from top down and replace keywords with vectors, then convert data to code
    We can have 1 map with code and one with definitions, although this means we might not synchronise properly

Example
(def core/nand-hidden (fn [a b] (bit-and-not a b)))

Input block is changeable by user
(def core/input-block {:state 0}) ;consider making these one def, these might have to be mutable
(def core/output-block {:state 0})
Output block is attached to a gate

{:nand #(core/nand-hidden % %)
 :not '(:nand 'a 'a)
 :and '(:not '(:nand 'a 'b))
}

Think about ways for usnig this immutably, although having it atomic is not a bad compromise

The gates are stored in a data map and then converted to a function, where the values are a vector
The first element of the vector is a list, the second is a corresponding function
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
    figure way to convert function to static data structure and back

Steps:
    Backend - work out data structure
              convert between data structure and code
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
