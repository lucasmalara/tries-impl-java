package com.lucasmalara.datastruct.trie;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Trie {
    public final TrieNode root = new TrieNode();

    private Trie() {

    }

    public static Trie getInstance() {
        return new Trie();
    }

    public void insert(String word) {
        if (word != null) {
            TrieNode current = root;
            for (Character c : word.toCharArray())
                current = current.getNext(c);

            current.setTerminal(true);
        }
    }

    public boolean isEmpty() {
        return root.isEmpty();
    }

    public boolean search(String word) {
        Optional<TrieNode> nodeOptional = depthFirstSearch(word);
        return nodeOptional.map(TrieNode::isTerminal).orElse(false);
    }

    public Optional<TrieNode> depthFirstSearch(String word) {
        return depthFirstSearch(root, word);
    }

    private Optional<TrieNode> depthFirstSearch(TrieNode current, String word) {
        if(word == null)
            return Optional.empty();

        for (Character c : word.toCharArray()) {
            TrieNode byChar = current.getChild(c);
            if (byChar == null)
                return Optional.empty();

            current = byChar;
        }
        return Optional.of(current);
    }

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
