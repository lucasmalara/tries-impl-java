package com.lucasmalara.datastruct.trie;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Tree-based data structure known also as a {@code digital tree} or a {@code prefix tree}.
 * It can be used to locate particular keys from within a set.
 *
 * @implNote This implementation considered keys as a {@code String}.
 * A key is stored between multiple nodes linked to each other by an individual character of that key.
 * @see TrieNode
 */
public class Trie {

    /**
     * The root node to iterate through a whole trie.
     *
     * @implNote Root node should never be marked as a terminal.
     */
    public final TrieNode root = new TrieNode() {
        @Override
        public void setTerminal(boolean isTerminal) {
            // This overridden method ensures
            // that root is always not terminal - that is defined by a definition.
        }
    };

    /**
     * This constructor restricts instantiation of a trie.
     *
     * @see #empty()
     */
    private Trie() {
    }

    /**
     * @return an empty trie.
     */
    public static Trie empty() {
        return new Trie();
    }

    /**
     * <p>
     * This method insert a given {@code String} to this trie.
     * he given {@code String} is inserted in such a way,
     * that its each character is associated to a node.
     * </p>
     * <p>
     * If an {@code N} node does not contain a child node {@code C} associated with ith character,
     * then a {@code new} child node of an {@code N} node is created that is associated with that character,
     * and we move into that node;
     * <br>
     * Otherwise we move to node {@code C} that becomes a node {@code N},
     * and we check the same condition for that node.
     * </p>
     *
     * @param word a {@code String} to insert in this trie.
     */
    public void insert(String word) {
        if (word != null) {
            TrieNode current = root;
            for (Character c : word.toCharArray())
                current = current.nearestChild(c);

            current.setTerminal(true);
        }
    }

    /**
     * This method checks if trie is empty.
     *
     * @return {@code true} if {@link #root} does not have a child, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return root.isLeaf();
    }

    /**
     * This method search through this trie to find if a given {@code String} is in this trie
     * and a node associated with a last character of that {@code String} is marked as a terminal.
     *
     * @param word a {@code String} to search for in this trie.
     * @return {@code true} if this trie contains a given {@code String}
     * that its last character is marked as terminal in this trie,
     * {@code false} otherwise.
     * @see TrieNode#isTerminal()
     */
    public boolean search(String word) {
        Optional<TrieNode> nodeOptional = depthFirstSearch(word);
        return nodeOptional.map(TrieNode::isTerminal).orElse(false);
    }

    /**
     * This method is a modified implementation of a depth-first search algorithm.
     * Iteration starts from {@link #root} by ith character of a given {@code String}.
     *
     * @param word a {@code String} to iterate through.
     * @return {@code Optional} of node if a given {@code String} can be iterated from {@link #root},
     * empty {@code Optional} otherwise.
     */
    public Optional<TrieNode> depthFirstSearch(String word) {
        return depthFirstSearch(root, word);
    }

    /**
     * This method is a modified implementation of a depth-first search algorithm.
     * Iteration starts from a given node by ith character of a given {@code String}.
     *
     * @param current a trie node to traverse from.
     * @param word    a {@code String} to iterate through.
     * @return {@code Optional} of node if a given {@code String} can be iterated from a given node,
     * empty {@code Optional} otherwise.
     * @see #depthFirstSearch(String)
     */
    private Optional<TrieNode> depthFirstSearch(TrieNode current, String word) {
        if (word == null)
            return Optional.empty();

        for (Character c : word.toCharArray()) {
            TrieNode byChar = current.getChild(c);
            if (byChar == null)
                return Optional.empty();

            current = byChar;
        }
        return Optional.of(current);
    }

    /**
     * <p>
     * This method erases a given word stored in this trie by iterate through that word with nodes.
     * Iteration starts from the {@link #root} and a first character of that word.
     * </p>
     * <br>
     * <p>
     * If a given {@code String} cannot be iterated, then this trie does not contain it.
     * </p>
     * <br>
     * <p>
     * A word is literally erased iff it can be iterated to its last character
     * that must be associated with a leaf and marked as terminal.
     * </p>
     * <br>
     * <p>
     * If a node is not a leaf, then a given {@code String} is `just` erased,
     * that means is no longer considered a word in this trie,
     * which means that, that node is no longer marked as terminal.
     * <br>
     * However, none of the nodes are being removed.
     * </p>
     *
     * @param word a word to erase from this trie.
     * @return {@code true} if word was erased, {@code false} otherwise.
     */
    public boolean erase(String word) {
        Optional<TrieNode> nodeOptional = depthFirstSearch(word);
        boolean nodeFound = nodeOptional.isPresent();
        if (nodeFound)
            removeNodes(root, word, 0);

        return nodeFound;
    }

    /**
     * <p>
     * This method removes nodes based on a given word.
     * Iteration starts from the given: node and an ith character of a given word,
     * <br>
     * where {@code i := characterIndex}.
     * </p>
     * <br>
     * <p>
     * If a given word cannot be iterated from a given node by an ith character of that word,
     * then the subtrie does not contain that word.
     * </p>
     * <br>
     * <p>
     * If an iterated last character of that word is associated with a leaf marked as terminal,
     * then that node is removed,
     * and each previously iterated node is removed
     * until the first node marked as a terminal
     * that is the nearest to a node associated with that last character;
     * </p>
     * <br>
     * <p>
     * Otherwise a node associated with that last character is marked as not terminal
     * and none of the nodes are being removed.
     * </p>
     *
     * @param fromNode       a node to start iteration from.
     * @param word           a word to erase from this trie.
     * @param characterIndex an index of an ith character of a given {@code String}.
     * @return {@code true} if a node
     * associated with an ith character of the {@code String}
     * was removed from the subtrie,
     * {@code false} otherwise.
     */
    private boolean removeNodes(TrieNode fromNode, String word, int characterIndex) {
        if (word == null)
            return false;

        if (word.length() == characterIndex) {
            if (!fromNode.isTerminal())
                return false;

            // setTerminal(false) if isTerminal(): true
            fromNode.setTerminal(false);
            return fromNode.isLeaf();
        }

        if (word.length() > characterIndex) {
            char c = word.charAt(characterIndex);
            TrieNode byChar = fromNode.getChild(c);
            if (byChar == null)
                return false;

            // recursion
            boolean removeNext =
                    removeNodes(byChar, word, ++characterIndex) && !byChar.isTerminal();

            if (removeNext) {
                // remove a child associated with a given character
                // fromNode.removeChild(c): byChar
                fromNode.removeChild(c);
                return true;
            }
        }
        return false;
    }

    /**
     * @return {@code String} representation of this trie.
     */
    @Override
    public String toString() {
        Map<Character, TrieNode> children = root.children;
        if (children.isEmpty())
            return "{}";

        return children.entrySet()
                .stream()
                .map(entry -> STR."{\{entry.getKey()} -> \{entry.getValue()}}")
                .collect(Collectors.joining("\n"));
    }
}
