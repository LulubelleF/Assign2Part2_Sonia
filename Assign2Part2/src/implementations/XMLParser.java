package implementations;

import java.io.*;

public class XMLParser {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java XMLParser <filename>");
            return;
        }
        
        String filename = args[0];
        MyStack<String> stack = new MyStack<>();

        int lineNumber = 0;
        boolean hasRoot = false;
        boolean rootClosed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                int idx = 0;
                while ((idx = line.indexOf("<", idx)) != -1) {
                    int closeIdx = line.indexOf(">", idx);
                    if (closeIdx == -1) break;

                    String tag = line.substring(idx + 1, closeIdx).trim();

                    // Skip processing instructions and comments
                    if (tag.startsWith("?") || tag.startsWith("!--")) {
                        idx = closeIdx + 1;
                        continue;
                    }

                    // Self-closing tag
                    if (tag.endsWith("/")) {
                        idx = closeIdx + 1;
                        continue;
                    }

                    // Closing tag
                    if (tag.startsWith("/")) {
                        String tagName = tag.substring(1);
                        if (stack.isEmpty()) {
                            System.out.println("Line " + lineNumber + ": Unexpected closing tag </" + tagName + ">");
                        } else {
                            String openTag = stack.pop();
                            if (!openTag.equals(tagName)) {
                                System.out.println("Line " + lineNumber + ": Mismatched tag </" + tagName + ">, expected </" + openTag + ">");
                            }
                        }
                        if (stack.isEmpty()) rootClosed = true;
                    } else {
                        if (!hasRoot) hasRoot = true;
                        if (rootClosed) {
                            System.out.println("Line " + lineNumber + ": Content after root tag closed.");
                        }
                        String tagName = tag.split("\\s+")[0];
                        stack.push(tagName);
                    }

                    idx = closeIdx + 1;
                }
            }

            if (!hasRoot) {
                System.out.println("Missing root tag");
            } else if (!stack.isEmpty()) {
                while (!stack.isEmpty()) {
                    System.out.println("Unclosed tag: <" + stack.pop() + ">");
                }
            } else {
                System.out.println("XML document is constructed correctly.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
