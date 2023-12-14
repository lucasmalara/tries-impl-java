
## Abstract

<div align="justify">
A trie, also called a <code>digital tree</code> or a <code>prefix tree</code>, 
is a <code>tree data structure</code> used to locate particular keys from within a set. 
This implementation considered keys as a <b>strings</b>, since it is most commonly encountered. 
A key is stored between multiple nodes linked to each other by an individual <b>character</b> of a key.
To access a key from a trie, it is necessary to perform a <b>depth-first search</b> traverse algorithm.
<br>
As opposed to a <code>binary search tree</code>, a key associated with a node is not stored in it.
Instead, it is defined by a node position in a trie.
<br>
Every children of the node have a common string <b>prefix</b> associated with the parent node.
The root node is associated with an <b>empty string</b>.
</div>

## Common Operations

Operations and structures were implemented following OOP paradigm.

### Legend

- [x] implemented
- [ ] not yet implemented

### Operation

- [x] Insertion: `trie.insert(word: String): void`
- [x] Searching: `trie.search(word: String): boolean`
- [x] Deletion: `trie.erase(word: String): boolean`

## Applications

<div align="justify">
Tries are commonly used for <b>text prediction</b>, <b>autocompleting dictionaries</b>
and <b>approximate matching algorithms</b>. 
They are especially efficient in <b>spell checking</b> and <b>longest prefix match</b> algorithms, 
since they occupy less space 
and enable faster searching when the set contains <i>large</i> number of <i>short</i> strings.
</div>

## Project Details

- Language: `Java 21 (Preview)`
- Built with: `Gradle (Kotlin)`
- Test framework: `JUnit 5`

## How To Use

If you would like to use this implementation, 
you should know that at the moment when it was written
**(12.12.2023)** the code language level is set to `Java 21 Preview`.
Hence, if you are going to compile the code containing this implementation,
you should have JDK 21 or possible any newer and add a flag `--enable-preview`, e.g.:

- Javac (Compile to bytecode)

```shell
    javac --enable-preview Main.java
```

- Java (Run)

```shell
    java --enable-preview Main
```

- Gradle (Kotlin)

`build.gradle.kts`:

```kotlin
    // ...
    tasks.test {
        // ...
        jvmArgs("--enable-preview")
    }

    tasks.withType<JavaCompile> {
        // ...
        options.compilerArgs.add("--enable-preview")
    }
```

- Gradle (Groovy)

`build.gradle`:

```groovy
    // ...
    test {
        // ...
        jvmArgs(['--enable-preview'])
    }

    tasks.withType(JavaCompile).each {
        // ...
        it.options.compilerArgs.add('--enable-preview')
    }
```

- Maven

`pom.xml`:

```xml
<!-- ... -->
<build>
    <plugins>
        <!-- ... -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>21</source>
                <target>21</target>
                <compilerArgs>
                    --enable-preview
                </compilerArgs>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## Author

[@lucasmalara](https://github.com/lucasmalara "author")
