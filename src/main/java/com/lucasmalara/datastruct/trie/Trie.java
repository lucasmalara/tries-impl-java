package com.lucasmalara.datastruct.trie;

import java.util.Optional;

public class Trie {
    public final TrieNode root = new TrieNode();

    private Trie() {

    }

    public static Trie getInstance() {
        return new Trie();
    }

    public void insert(String word) {

    }

    public boolean isEmpty() {
        return false;
    }

    public boolean search(String word) {
        return false;
    }

    public Optional<TrieNode> depthFirstSearch(String word) {
        return Optional.empty();
    }
}
