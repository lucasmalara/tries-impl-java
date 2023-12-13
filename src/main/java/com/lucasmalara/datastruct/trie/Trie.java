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
     */
    public final TrieNode root = new TrieNode();

    /**
     * This constructor restricts instantiation of a trie.
     *
     * @see #empty()
     */
    private Trie() {
    }

    /**
     * @return instance of an empty trie.
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
     * and its last character is marked as a terminal.
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
     * @return {@code String} representation of this trie.
     *
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
