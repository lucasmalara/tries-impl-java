package com.lucasmalara.datastruct.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TrieTest {

    @Test
    void givenEmptyTrie_WhenTrieIsEmpty_ThenTrieIsEmptyIsTrue() {
        Trie trie = Trie.getInstance();
        assertTrue(trie.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenNullAndEmptySource_whenInsertInTrie_ThenTrieIsEmptyIsTrue(String value) {
        Trie trie = Trie.getInstance();
        trie.insert(value);
        assertTrue(trie.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenNullAndEmptySource_whenInsertInTrie_ThenTrieSearchFalse(String value) {
        Trie trie = Trie.getInstance();
        assertFalse(trie.search(value));
    }

    @Test
    void givenEmptyNode_WhenIsEmpty_ThenIsTrue() {
        Trie trie = Trie.getInstance();
        assertTrue(trie.isEmpty());
        assertTrue(trie.root.isEmpty());
    }

    @Test
    void givenChild_WhenIsEmpty_ThenEqualsExpected() {
        Trie trie = Trie.getInstance();
        trie.insert("a");
        assertTrue(trie.root.getChild('a').isEmpty());
        trie.insert("ab");
        assertFalse(trie.root.getChild('a').isEmpty());
        assertTrue(trie.root.getChild('a').getChild('b').isEmpty());
        trie.insert("ac");
        assertTrue(trie.root.getChild('a').getChild('c').isEmpty());
    }

    @Test
    void givenChild_WhenIsTerminal_ThenEqualsExpected() {
        Trie trie = Trie.getInstance();
        assertFalse(trie.root.isTerminal());
        trie.insert("ab");
        assertFalse(trie.root.getChild('a').isTerminal());
        assertTrue(trie.root.getChild('a').getChild('b').isTerminal());
        trie.insert("abc");
        assertTrue(trie.root.getChild('a').getChild('b').isTerminal());
        assertTrue(trie.root.getChild('a').getChild('b').getChild('c').isTerminal());
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', '1', '?', '\\', '\n', ' '})
    void givenCharacters_WhenGetChild_ThenIsNull(char c) {
        Trie trie = Trie.getInstance();
        assertNull(trie.root.getChild(c));
    }

    @ParameterizedTest
    @NullSource
    void givenNullSource_WhenGetChild_ThenIsNull(Character c) {
        Trie trie = Trie.getInstance();
        assertNull(trie.root.getChild(c));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', '1', '?', '\\', '\n', ' '})
    void givenCharactersInsertInTrie_WhenGetChild_ThenIsNotNull(char c) {
        Trie trie = Trie.getInstance();
        trie.insert(String.valueOf(c));
        assertNotNull(trie.root.getChild(c));
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', '1', '?', '\\', '\n', ' '})
    void givenCharacters_WhenNodeNext_ThenReturnNewNode(char c) {
        Trie trie = Trie.getInstance();
        TrieNode expected = trie.root.getChild(c);
        TrieNode next = trie.root.getNext(c);
        assertNotEquals(expected, next);
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', '1', '?', '\\', '\n', ' '})
    void givenCharactersInsertInTrie_WhenNodeNext_ThenReturnSameNode(char c) {
        Trie trie = Trie.getInstance();
        trie.insert(String.valueOf(c));
        TrieNode expected = trie.root.getChild(c);
        TrieNode next = trie.root.getNext(c);
        assertEquals(expected, next);
    }

    @Test
    void givenChild_WhenNodeNext_ThenReturnSameNode() {
        Trie trie = Trie.getInstance();
        trie.insert("a");
        TrieNode expected = trie.root.getChild('a');
        TrieNode next = trie.root.getNext('a');
        assertEquals(expected, next);
    }

    @Test
    void givenChild_WhenSetTerminalFalse_ThenIsTerminalIsFalse() {
        Trie trie = Trie.getInstance();
        trie.insert("a");
        TrieNode child = trie.root.getChild('a');
        assertTrue(child.isTerminal());
        child.setTerminal(false);
        assertFalse(child.isTerminal());
    }

    @ParameterizedTest
    @NullSource
    void givenNullSourceInsertInTrie_WhenPerformDFS_ThenNodeNotFound(String value) {
        Trie trie = Trie.getInstance();
        trie.insert(value);
        Optional<TrieNode> nodeOptional = trie.depthFirstSearch(value);
        assertTrue(nodeOptional.isEmpty());
    }

    @ParameterizedTest
    @EmptySource
    void givenEmptySourceInsertInTrie_WhenPerformDFS_ThenNodeFound(String value) {
        Trie trie = Trie.getInstance();
        trie.insert(value);
        Optional<TrieNode> nodeOptional = trie.depthFirstSearch(value);
        assertTrue(nodeOptional.isPresent());
    }

    @Nested
    class TrieExamplesTest {

        private final List<String> examples = new ArrayList<>();

        private static List<String> examplesRandomOrder() {
            List<String> examples =
                    new ArrayList<>(Stream.<String>builder()
                            .add("bat")
                            .add("bar")
                            .add("barn")
                            .add("barm")
                            .add("cat")
                            .add("car")
                            .add("carp")
                            .add("cell")
                            .add("cola")
                            .add("cut")
                            .add("dog")
                            .add("r")
                            .build()
                            .toList());
            Collections.shuffle(examples, new Random(examples.size()));
            return examples;
        }

        private void assertChildrenContains(Map<Character, TrieNode> current, char... expected) {
            for (char c : expected) {
                assertTrue(current.containsKey(c));
                assertEquals(expected.length, current.size());
            }
        }

        @BeforeEach
        void setUp() {
            examples.addAll(examplesRandomOrder());
        }

        @Test
        void givenStrings_WhenInsertInTrie_ThenDFSFoundNode() {
            Trie trie = Trie.getInstance();
            examples.forEach(s -> {
                trie.insert(s);
                Optional<TrieNode> nodeOptional = trie.depthFirstSearch(s);
                assertTrue(nodeOptional.isPresent());
            });
        }

        @Test
        void givenStrings_WhenInsertInTrie_ThenSearchIsTrue() {
            Trie trie = Trie.getInstance();
            examples.forEach(s -> {
                trie.insert(s);
                assertTrue(trie.search(s));
            });
        }

        @Test
        void givenTrieNodes_WhenGetChildren_ThenContainsExactlyExpected() {
            Trie trie = Trie.getInstance();
            examples.forEach(trie::insert);
            Map<Character, TrieNode> start = trie.root.children;
            assertChildrenContains(start, 'b', 'c', 'd', 'r');

            TrieNode temp;

            // CASE: b...
            temp = start
                    .get('b');
            assertChildrenContains(temp.children, 'a');

            // CASE: ba...
            temp = start
                    .get('b')
                    .getChild('a');
            assertChildrenContains(temp.children, 't', 'r');

            // CASE: bat
            temp = start
                    .get('b')
                    .getChild('a')
                    .getChild('t');
            assertTrue(temp.isEmpty());

            // CASE: bar
            temp = start
                    .get('b')
                    .getChild('a')
                    .getChild('r');
            assertChildrenContains(temp.children, 'n', 'm');

            // CASE: barn
            temp = start
                    .get('b')
                    .getChild('a')
                    .getChild('r')
                    .getChild('n');
            assertTrue(temp.isEmpty());

            // CASE: barm
            temp = start
                    .get('b')
                    .getChild('a')
                    .getChild('r')
                    .getChild('m');
            assertTrue(temp.isEmpty());

            // CASE: c...
            temp = start
                    .get('c');
            assertChildrenContains(temp.children, 'a', 'e', 'u', 'o');

            // CASE: ca...
            temp = start
                    .get('c')
                    .getChild('a');
            assertChildrenContains(temp.children, 't', 'r');

            // CASE: cat
            temp = start
                    .get('c')
                    .getChild('a')
                    .getChild('t');
            assertTrue(temp.isEmpty());

            // CASE: car...
            temp = start
                    .get('c')
                    .getChild('a')
                    .getChild('r');
            assertChildrenContains(temp.children, 'p');

            // CASE: carp
            temp = start
                    .get('c')
                    .getChild('a')
                    .getChild('r')
                    .getChild('p');
            assertTrue(temp.isEmpty());

            //CASE: cu..
            temp = start
                    .get('c')
                    .getChild('u');
            assertChildrenContains(temp.children, 't');

            //CASE: cut
            temp = start
                    .get('c')
                    .getChild('u')
                    .getChild('t');
            assertTrue(temp.isEmpty());

            //CASE: ce...
            temp = start
                    .get('c')
                    .getChild('e');
            assertChildrenContains(temp.children, 'l');

            //CASE: cel...
            temp = start
                    .get('c')
                    .getChild('e')
                    .getChild('l');
            assertChildrenContains(temp.children, 'l');

            // CASE: cell
            temp = start
                    .get('c')
                    .getChild('e')
                    .getChild('l')
                    .getChild('l');
            assertTrue(temp.isEmpty());

            // CASE: co...
            temp = start
                    .get('c')
                    .getChild('o');
            assertChildrenContains(temp.children, 'l');

            // CASE: col...
            temp = start
                    .get('c')
                    .getChild('o')
                    .getChild('l');
            assertChildrenContains(temp.children, 'a');

            // CASE: cola
            temp = start
                    .get('c')
                    .getChild('o')
                    .getChild('l')
                    .getChild('a');
            assertTrue(temp.isEmpty());

            // CASE: d...
            temp = start
                    .get('d');
            assertChildrenContains(temp.children, 'o');

            // CASE: do...
            temp = start
                    .get('d')
                    .getChild('o');
            assertChildrenContains(temp.children, 'g');

            // CASE: dog
            temp = start
                    .get('d')
                    .getChild('o')
                    .getChild('g');
            assertTrue(temp.isEmpty());

            // CASE: r...
            // CASE: r
            temp = start
                    .get('r');
            assertTrue(temp.isEmpty());
        }

        @Test
        void givenTrieNodes_WhenGetChild_ThenIsTerminalEqualExpected() {
            Trie trie = Trie.getInstance();
            examples.forEach(trie::insert);
            Map<Character, TrieNode> start = trie.root.children;
            TrieNode temp;

            // CASE: b...
            temp = start
                    .get('b');
            assertFalse(temp.isTerminal());

            // CASE: ba...
            temp = start
                    .get('b')
                    .getChild('a');
            assertFalse(temp.isTerminal());

            // CASE: bat
            temp = start
                    .get('b')
                    .getChild('a')
                    .getChild('t');
            assertTrue(temp.isTerminal());

            // CASE: bar
            temp = start
                    .get('b')
                    .getChild('a')
                    .getChild('r');
            assertTrue(temp.isTerminal());

            // CASE: barn
            temp = start
                    .get('b')
                    .getChild('a')
                    .getChild('r')
                    .getChild('n');
            assertTrue(temp.isTerminal());

            // CASE: barm
            temp = start
                    .get('b')
                    .getChild('a')
                    .getChild('r')
                    .getChild('m');
            assertTrue(temp.isTerminal());

            // CASE: c...
            temp = start
                    .get('c');
            assertFalse(temp.isTerminal());

            // CASE: ca...
            temp = start
                    .get('c')
                    .getChild('a');
            assertFalse(temp.isTerminal());

            // CASE: cat
            temp = start
                    .get('c')
                    .getChild('a')
                    .getChild('t');
            assertTrue(temp.isTerminal());

            // CASE: car...
            temp = start
                    .get('c')
                    .getChild('a')
                    .getChild('r');
            assertTrue(temp.isTerminal());

            // CASE: carp
            temp = start
                    .get('c')
                    .getChild('a')
                    .getChild('r')
                    .getChild('p');
            assertTrue(temp.isTerminal());

            //CASE: cu..
            temp = start
                    .get('c')
                    .getChild('u');
            assertFalse(temp.isTerminal());

            //CASE: cut
            temp = start
                    .get('c')
                    .getChild('u')
                    .getChild('t');
            assertTrue(temp.isTerminal());

            //CASE: ce...
            temp = start
                    .get('c')
                    .getChild('e');
            assertFalse(temp.isTerminal());

            //CASE: cel...
            temp = start
                    .get('c')
                    .getChild('e')
                    .getChild('l');
            assertFalse(temp.isTerminal());

            // CASE: cell
            temp = start
                    .get('c')
                    .getChild('e')
                    .getChild('l')
                    .getChild('l');
            assertTrue(temp.isTerminal());

            // CASE: co...
            temp = start
                    .get('c')
                    .getChild('o');
            assertFalse(temp.isTerminal());

            // CASE: col...
            temp = start
                    .get('c')
                    .getChild('o')
                    .getChild('l');
            assertFalse(temp.isTerminal());

            // CASE: cola
            temp = start
                    .get('c')
                    .getChild('o')
                    .getChild('l')
                    .getChild('a');
            assertTrue(temp.isTerminal());

            // CASE: d...
            temp = start
                    .get('d');
            assertFalse(temp.isTerminal());

            // CASE: do...
            temp = start
                    .get('d')
                    .getChild('o');
            assertFalse(temp.isTerminal());

            // CASE: dog
            temp = start
                    .get('d')
                    .getChild('o')
                    .getChild('g');
            assertTrue(temp.isTerminal());

            // CASE: r...
            // CASE: r
            temp = start
                    .get('r');
            assertTrue(temp.isTerminal());
        }
    }
}
