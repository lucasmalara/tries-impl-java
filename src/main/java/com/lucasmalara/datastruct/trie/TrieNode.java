package com.lucasmalara.datastruct.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    public final Map<Character, TrieNode> children = new HashMap<>();
    private boolean isTerminal;

    public boolean isEmpty() {
        return children.isEmpty();
    }

    public TrieNode removeChild(Character c) {
        return children.remove(c);
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    public TrieNode getNext(Character c) {
        return children.computeIfAbsent(c, _ -> new TrieNode());
    }

    public TrieNode getChild(Character c) {
        return children.get(c);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!isEmpty())
            sb.append(children).append(", ");

        return sb.append(isTerminal).toString();
    }
}
