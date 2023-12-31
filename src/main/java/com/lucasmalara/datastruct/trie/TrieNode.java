package com.lucasmalara.datastruct.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a node in a trie.
 *
 * @see Trie
 */
public class TrieNode {

    /**
     * Children of this node associated with a unique character.
     */
    public final Map<Character, TrieNode> children = new HashMap<>();

    /**
     * {@code Boolean} value determining if this node is associated with
     * a last character of a word contained by the trie.
     */
    private boolean isTerminal;

    /**
     * This method checks if this node is a leaf.
     *
     * @return {@code true} if {@link #children} is empty, {@code false} otherwise.
     */
    public boolean isLeaf() {
        return children.isEmpty();
    }

    /**
     * This method removes a child of this node by a given character.
     *
     * @param c a character associated with a node to remove.
     * @return removed child if exists, {@code null} otherwise.
     */
    public TrieNode removeChild(char c) {
        return children.remove(c);
    }

    /**
     * @return {@code true} if this node is associated with a last character of a word contained by the trie,
     * {@code false} otherwise.
     */
    public boolean isTerminal() {
        return isTerminal;
    }


    /**
     * @param isTerminal a value determining whether this node
     *                   is associated with a last character of a word in the trie.
     */
    public void setTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    /**
     * This method returns the nearest possible node associated with a given character.
     *
     * @param c a character associated with a possible node.
     * @return a child associated with a given character if exists, new child associated by that character otherwise.
     */
    public TrieNode nearestChild(char c) {
        return children.computeIfAbsent(c, _ -> new TrieNode());
    }

    /**
     * This method returns a child of this node by a given character.
     *
     * @param c a character associated with a node to retrieve.
     * @return a child associated with a given character if exists, null otherwise.
     */
    public TrieNode getChild(char c) {
        return children.get(c);
    }

    /**
     * @return {@code String} representation of this node.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!isLeaf())
            sb.append(children).append(", ");

        return sb.append(isTerminal).toString();
    }
}
