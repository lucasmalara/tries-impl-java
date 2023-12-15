
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

## Table Of Content

- [Common Operations](#common-operations)
  - [Legend](#legend)
  - [Operation](#operation)
- [Applications](#applications)
- [Project Details](#project-details)
- [How To Use](#how-to-use)
  - [Local dependency](#local-dependency)
  - [Source code](#source-code)
  - [Compilation](#compilation)
- [Author](#author)


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

### Local dependency

1. Download a jar file attached to the release:
   [v1.0](https://github.com/lucasmalara/tries-impl-java/releases/tag/v1.0 "v1.0").

2. You can verify SHA of downloaded file (details are in the release description).

3. Copy that jar file into a subdirectory of your project directory, 
e.g.: `YourProject/libs/` and add as local dependency:

- Gradle (Kotlin):

Add into `dependencies` function in `build.gradle.kts`:

```kotlin
    implementation(files("./libs/Trie-1.0.jar"))
```

- Gradle (Groovy):

Add into `dependencies` function in `build.gradle`:

```groovy
    implementation files('./libs/Trie-1.0.jar')
```

### Source code

Copy files: `Trie.java` and `TrieNode.java` into a java package of your choice in your project.

### Compilation

If you would like to use this implementation, 
you should know that at the moment when it was written
**(12.12.2023)**, the code is written in `Java 21 Preview`.
Hence, if you are going to compile and run the project containing this implementation,
you should use JDK 21 or possible any newer and add a flag `--enable-preview`, e.g.:

- Javac (Compile to bytecode)

```shell
    javac --enable-preview YourMainClass.java
```

- Java (Run)

```shell
    java --enable-preview YourMainClass
```

- Gradle (Kotlin)

`build.gradle.kts`:

```kotlin
    // ...
    // NEEDED IF YOU HAVE TEST CLASSES
    tasks.test {
        // ...
        jvmArgs("--enable-preview")
    }

    // REQUIRED
    tasks.withType<JavaCompile> {
        // ...
        options.compilerArgs.add("--enable-preview")
    }
```

- Gradle (Groovy)

`build.gradle`:

```groovy
    // ...
    // NEEDED IF YOU HAVE TEST CLASSES
    test {
        // ...
        jvmArgs(['--enable-preview'])
    }

    // REQUIRED
    tasks.withType(JavaCompile).each {
        // ...
        it.options.compilerArgs.add('--enable-preview')
    }
```

- Compile & Run with IntelliJ:

1. Open Run/Debug Configuration of your project.
2. Expand list: Modify options.
3. Add VM option -> shortcut: `ALT + V`.
4. Paste `--enable-preview` into the VM options.
5. Confirm by clicking OK.

## Author

[@lucasmalara](https://github.com/lucasmalara "author")
