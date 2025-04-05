XML Parser Program

This program checks XML files for mismatched or unclosed tags.

Requirements:
- Java 8 or higher

How to Run:
1. Ensure you have the Parser.jar file in your working directory.
2. Run the following command to parse an XML file:

   java -jar Parser.jar <path-to-xml-file>

Examples:
- To parse sample1.xml:
  java -jar Parser.jar res/sample1.xml

- To parse sample2.xml:
  java -jar Parser.jar res/sample2.xml

Output:
- The program will print any mismatched or unclosed tags.
- If the XML is valid, it will print: "XML document is constructed correctly."
