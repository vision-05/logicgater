# logicgater
Customisable logic gate sim, with one fundamental part: the nand gate

Specification for data:

(think about connections)

Logic gates:
    Stored in a map, name and corresponding function in data form.
    We need function to translate between data and code, parse the map from top down and replace keywords with vectors, then convert data to code
    We can have 1 map with code and one with definitions, although this means we might not synchronise properly

Example
(def core/nand-hidden (fn [a b] (nand a b)))

Input block is changeable by user
(def core/input-block {:state 0}) ;consider making these one def, these might have to be mutable
(def core/output-block {:state 0})
Output block is attached to a gate

{:core/nand [core/nand-hidden :a :b]
 :usr/not [:core/nand :a :a]
 :usr/and [:usr/not [:core/nand :a :b]]
}

This will be exposed to user as drag and drop blocks with wire connections between them. Connection from output to input will put previous function inside input. Think about how to reuse outputs, we can bind in a let

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