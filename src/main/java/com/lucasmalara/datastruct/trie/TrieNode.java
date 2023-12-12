package com.lucasmalara.datastruct.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    public final Map<Character, TrieNode> children = new HashMap<>();
    private boolean isTerminal;

    public boolean isEmpty() {
        return false;
    }

    public TrieNode removeChild(Character c) {
        return null;
    }

    public boolean isTerminal() {
        return false;
    }

    public void setTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    public TrieNode getNext(Character c) {
        return null;
    }

    public TrieNode getChild(Character c) {
        return null;
    }
}
