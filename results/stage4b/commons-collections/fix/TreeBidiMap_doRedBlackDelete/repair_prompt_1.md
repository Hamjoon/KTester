


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.collections4.bidimap;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;
import java.util.ConcurrentModificationException;

@ExtendWith(MockitoExtension.class)
class TreeBidiMap_doRedBlackDelete_Test {

    private TreeBidiMap<Integer, String> treeBidiMap;

    private TreeBidiMap.Node<Integer, String> nodeToDelete;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the TreeBidiMap before each test
        treeBidiMap = new TreeBidiMap<>();
        // Create a node to delete (as an example, you may want to add more nodes to the tree)
        nodeToDelete = new TreeBidiMap.Node<>(1, "One");
        // Assuming we add the node to the treeBidiMap before testing deletion
        treeBidiMap.put(nodeToDelete.getKey(), nodeToDelete.getValue());
    }

    @AfterEach
    void teardownAfterEach() {
        // Perform any necessary cleanup after each test
        // Clear the map after each test
        treeBidiMap.clear();
    }

    private void invokeDoRedBlackDelete(TreeBidiMap.Node<Integer, String> node) throws Exception {
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete(TreeBidiMap.Node)");
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, node);
    }

    @Test
    void testDoRedBlackDelete() {
        // Given
        treeBidiMap.put(2, "Two");
        treeBidiMap.put(3, "Three");
        // When
        // Remove should return void
        treeBidiMap.remove(nodeToDelete);
        // Then
        assertNull(treeBidiMap.get(1), "Deleted node should not be found in the map");
    }

    @Test
    void testDoRedBlackDelete_NodeWithBothChildren() throws Exception {
        // Given: A node with both left and right children
        TreeBidiMap.Node<Integer, String> leftChild = new TreeBidiMap.Node<>(0, "Zero");
        TreeBidiMap.Node<Integer, String> rightChild = new TreeBidiMap.Node<>(2, "Two");
        treeBidiMap.put(leftChild.getKey(), leftChild.getValue());
        treeBidiMap.put(rightChild.getKey(), rightChild.getValue());
        treeBidiMap.put(3, "Three");
        // Link the children to the node to delete
        Method setLeft = TreeBidiMap.Node.class.getDeclaredMethod("setLeft(TreeBidiMap.Node, TreeBidiMap.DataElement)");
        Method setRight = TreeBidiMap.Node.class.getDeclaredMethod("setRight(TreeBidiMap.Node, TreeBidiMap.DataElement)");
        setLeft.setAccessible(true);
        setRight.setAccessible(true);
        setLeft.invoke(nodeToDelete, leftChild, TreeBidiMap.DataElement.KEY);
        setRight.invoke(nodeToDelete, rightChild, TreeBidiMap.DataElement.KEY);
        // When: The node is deleted
        invokeDoRedBlackDelete(nodeToDelete);
        // Then: The node is replaced with the next greater node
        assertNull(treeBidiMap.get(1), "Deleted node should not be found in the map");
        assertNotNull(treeBidiMap.get(2), "Next greater node should still exist");
    }

    @Test
    void testDoRedBlackDelete_NodeWithLeftChildOnly() throws Exception {
        // Given: A node with left child only
        TreeBidiMap.Node<Integer, String> leftChild = new TreeBidiMap.Node<>(0, "Zero");
        treeBidiMap.put(leftChild.getKey(), leftChild.getValue());
        // Link the left child to the node to delete
        Method setLeft = TreeBidiMap.Node.class.getDeclaredMethod("setLeft(TreeBidiMap.Node, TreeBidiMap.DataElement)");
        setLeft.setAccessible(true);
        setLeft.invoke(nodeToDelete, leftChild, TreeBidiMap.DataElement.KEY);
        // When: The node is deleted
        invokeDoRedBlackDelete(nodeToDelete);
        // Then: The left child becomes the new parent node
        assertNull(treeBidiMap.get(1), "Deleted node should not be found in the map");
        assertNotNull(treeBidiMap.get(0), "Left child should now be the root");
    }

    @Test
    void testDoRedBlackDelete_NodeWithRightChildOnly() throws Exception {
        // Given: A node with right child only
        TreeBidiMap.Node<Integer, String> rightChild = new TreeBidiMap.Node<>(2, "Two");
        treeBidiMap.put(rightChild.getKey(), rightChild.getValue());
        // Link the right child to the node to delete
        Method setRight = TreeBidiMap.Node.class.getDeclaredMethod("setRight(TreeBidiMap.Node, TreeBidiMap.DataElement)");
        setRight.setAccessible(true);
        setRight.invoke(nodeToDelete, rightChild, TreeBidiMap.DataElement.KEY);
        // When: The node is deleted
        invokeDoRedBlackDelete(nodeToDelete);
        // Then: The right child becomes the new parent node
        assertNull(treeBidiMap.get(1), "Deleted node should not be found in the map");
        assertNotNull(treeBidiMap.get(2), "Right child should now be the root");
    }

    @Test
    void testDoRedBlackDelete_NodeWithNoChildren() throws Exception {
        // Given: A node with no children
        // When: The node is deleted
        invokeDoRedBlackDelete(nodeToDelete);
        // Then: The node is removed
        assertNull(treeBidiMap.get(1), "Deleted node should not be found in the map");
    }

    @Test
    void testDoRedBlackDelete_RootNodeDeletion() throws Exception {
        // Given: The node to delete is the root node
        // When: The node is deleted
        invokeDoRedBlackDelete(nodeToDelete);
        // Then: The tree becomes empty
        assertTrue(treeBidiMap.isEmpty(), "Tree should be empty after root node deletion");
    }

    @Test
    void testDoRedBlackDelete_BlackNodeWithNoChildren() throws Exception {
        // Given: A black node with no children
        // Assume nodeToDelete is black (this would be set in the actual implementation)
        // When: The node is deleted
        invokeDoRedBlackDelete(nodeToDelete);
        // Then: The fixup method should be called (this would be verified in a real scenario)
        assertNull(treeBidiMap.get(1), "Deleted node should not be found in the map");
    }

    @Test
    void testDoRedBlackDelete_RedNodeWithNoChildren() throws Exception {
        // Given: A red node with no children
        // Assume nodeToDelete is red (this would be set in the actual implementation)
        // When: The node is deleted
        invokeDoRedBlackDelete(nodeToDelete);
        // Then: The node is removed without needing fixup
        assertNull(treeBidiMap.get(1), "Deleted node should not be found in the map");
    }

    @Test
    void testDoRedBlackDelete_NodeIsNull() {
        // When: The deleted node is null
        assertThrows(NullPointerException.class, () -> invokeDoRedBlackDelete(null), "Should throw NullPointerException for null node");
    }

    @Test
    void testDoRedBlackDelete_NodeWithInvalidState() {
        // Given: A node in an invalid state
        // Example of invalid state
        TreeBidiMap.Node<Integer, String> invalidNode = new TreeBidiMap.Node<>(-1, null);
        // When: The node is deleted
        assertThrows(IllegalArgumentException.class, () -> invokeDoRedBlackDelete(invalidNode), "Should throw IllegalArgumentException for invalid node");
    }

    @Test
    void testDoRedBlackDelete_ConcurrentModification() throws Exception {
        // Given: A node being modified while deletion occurs
        TreeBidiMap.Node<Integer, String> concurrentNode = new TreeBidiMap.Node<>(1, "One");
        treeBidiMap.put(concurrentNode.getKey(), concurrentNode.getValue());
        // Simulate concurrent modification
        Thread concurrentThread = new Thread(() -> {
            treeBidiMap.put(4, "Four");
        });
        concurrentThread.start();
        // When: The node is deleted
        assertThrows(ConcurrentModificationException.class, () -> invokeDoRedBlackDelete(concurrentNode), "Should throw ConcurrentModificationException");
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ TreeBidiMap_doRedBlackDelete_Test ✔
│     ├─ testDoRedBlackDelete_NodeWithRightChildOnly() ✘ org.apache.commons.collections4.bidimap.TreeBidiMap$Node.setRight(TreeBidiMap.Node, TreeBidiMap.DataElement)()
│     ├─ testDoRedBlackDelete_NodeWithBothChildren() ✘ org.apache.commons.collections4.bidimap.TreeBidiMap$Node.setLeft(TreeBidiMap.Node, TreeBidiMap.DataElement)()
│     ├─ testDoRedBlackDelete() ✘ class org.apache.commons.collections4.bidimap.TreeBidiMap$Node cannot be cast to class java.lang.Comparable (org.apache.commons.collections4.bidimap.TreeBidiMap$Node is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap')
│     ├─ testDoRedBlackDelete_NodeWithInvalidState() ✘ Should throw IllegalArgumentException for invalid node ==> Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.NoSuchMethodException>
│     ├─ testDoRedBlackDelete_RootNodeDeletion() ✘ org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
│     ├─ testDoRedBlackDelete_NodeIsNull() ✘ Should throw NullPointerException for null node ==> Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.NoSuchMethodException>
│     ├─ testDoRedBlackDelete_RedNodeWithNoChildren() ✘ org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
│     ├─ testDoRedBlackDelete_NodeWithLeftChildOnly() ✘ org.apache.commons.collections4.bidimap.TreeBidiMap$Node.setLeft(TreeBidiMap.Node, TreeBidiMap.DataElement)()
│     ├─ testDoRedBlackDelete_NodeWithNoChildren() ✘ org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
│     ├─ testDoRedBlackDelete_ConcurrentModification() ✘ Should throw ConcurrentModificationException ==> Unexpected exception type thrown, expected: <java.util.ConcurrentModificationException> but was: <java.lang.NoSuchMethodException>
│     └─ testDoRedBlackDelete_BlackNodeWithNoChildren() ✘ org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (11):
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_NodeWithRightChildOnly()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_NodeWithRightChildOnly', methodParameterTypes = '']
    => java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap$Node.setRight(TreeBidiMap.Node, TreeBidiMap.DataElement)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_NodeWithRightChildOnly(TreeBidiMap_doRedBlackDelete_Test.java:98)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_NodeWithBothChildren()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_NodeWithBothChildren', methodParameterTypes = '']
    => java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap$Node.setLeft(TreeBidiMap.Node, TreeBidiMap.DataElement)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_NodeWithBothChildren(TreeBidiMap_doRedBlackDelete_Test.java:63)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete', methodParameterTypes = '']
    => java.lang.ClassCastException: class org.apache.commons.collections4.bidimap.TreeBidiMap$Node cannot be cast to class java.lang.Comparable (org.apache.commons.collections4.bidimap.TreeBidiMap$Node is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap')
       org.apache.commons.collections4.bidimap.TreeBidiMap.lookup(TreeBidiMap.java:1783)
       org.apache.commons.collections4.bidimap.TreeBidiMap.lookupKey(TreeBidiMap.java:1795)
       org.apache.commons.collections4.bidimap.TreeBidiMap.doRemoveKey(TreeBidiMap.java:1423)
       org.apache.commons.collections4.bidimap.TreeBidiMap.remove(TreeBidiMap.java:1996)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete(TreeBidiMap_doRedBlackDelete_Test.java:49)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_NodeWithInvalidState()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_NodeWithInvalidState', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Should throw IllegalArgumentException for invalid node ==> Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.NoSuchMethodException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3131)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_NodeWithInvalidState(TreeBidiMap_doRedBlackDelete_Test.java:158)
       [...]
     Caused by: java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.invokeDoRedBlackDelete(TreeBidiMap_doRedBlackDelete_Test.java:37)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.lambda$testDoRedBlackDelete_NodeWithInvalidState$1(TreeBidiMap_doRedBlackDelete_Test.java:158)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_RootNodeDeletion()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_RootNodeDeletion', methodParameterTypes = '']
    => java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.invokeDoRedBlackDelete(TreeBidiMap_doRedBlackDelete_Test.java:37)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_RootNodeDeletion(TreeBidiMap_doRedBlackDelete_Test.java:121)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_NodeIsNull()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_NodeIsNull', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Should throw NullPointerException for null node ==> Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.NoSuchMethodException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3131)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_NodeIsNull(TreeBidiMap_doRedBlackDelete_Test.java:149)
       [...]
     Caused by: java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.invokeDoRedBlackDelete(TreeBidiMap_doRedBlackDelete_Test.java:37)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.lambda$testDoRedBlackDelete_NodeIsNull$0(TreeBidiMap_doRedBlackDelete_Test.java:149)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_RedNodeWithNoChildren()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_RedNodeWithNoChildren', methodParameterTypes = '']
    => java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.invokeDoRedBlackDelete(TreeBidiMap_doRedBlackDelete_Test.java:37)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_RedNodeWithNoChildren(TreeBidiMap_doRedBlackDelete_Test.java:141)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_NodeWithLeftChildOnly()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_NodeWithLeftChildOnly', methodParameterTypes = '']
    => java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap$Node.setLeft(TreeBidiMap.Node, TreeBidiMap.DataElement)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_NodeWithLeftChildOnly(TreeBidiMap_doRedBlackDelete_Test.java:82)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_NodeWithNoChildren()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_NodeWithNoChildren', methodParameterTypes = '']
    => java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.invokeDoRedBlackDelete(TreeBidiMap_doRedBlackDelete_Test.java:37)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_NodeWithNoChildren(TreeBidiMap_doRedBlackDelete_Test.java:112)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_ConcurrentModification()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_ConcurrentModification', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Should throw ConcurrentModificationException ==> Unexpected exception type thrown, expected: <java.util.ConcurrentModificationException> but was: <java.lang.NoSuchMethodException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3131)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_ConcurrentModification(TreeBidiMap_doRedBlackDelete_Test.java:172)
       [...]
     Caused by: java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.invokeDoRedBlackDelete(TreeBidiMap_doRedBlackDelete_Test.java:37)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.lambda$testDoRedBlackDelete_ConcurrentModification$3(TreeBidiMap_doRedBlackDelete_Test.java:172)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53)
       [...]
  JUnit Jupiter:TreeBidiMap_doRedBlackDelete_Test:testDoRedBlackDelete_BlackNodeWithNoChildren()
    MethodSource [className = 'org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test', methodName = 'testDoRedBlackDelete_BlackNodeWithNoChildren', methodParameterTypes = '']
    => java.lang.NoSuchMethodException: org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(TreeBidiMap.Node)()
       java.base/java.lang.Class.getDeclaredMethod(Class.java:2677)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.invokeDoRedBlackDelete(TreeBidiMap_doRedBlackDelete_Test.java:37)
       org.apache.commons.collections4.bidimap.TreeBidiMap_doRedBlackDelete_Test.testDoRedBlackDelete_BlackNodeWithNoChildren(TreeBidiMap_doRedBlackDelete_Test.java:131)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]

Test run finished after 392 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        11 tests found           ]
[         0 tests skipped         ]
[        11 tests started         ]
[         0 tests aborted         ]
[         0 tests successful      ]
[        11 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.collections4.bidimap.TreeBidiMap: 

 * Red-Black tree-based implementation of BidiMap where all objects added
 * implement the {@code Comparable} interface.
 * <p>
 * This class guarantees that the map will be in both ascending key order
 * and ascending value order, sorted according to the natural order for
 * the key's and value's classes.
 * </p>
 * <p>
 * This Map is intended for applications that need to be able to look
 * up a key-value pairing by either key or value, and need to do so
 * with equal efficiency.
 * </p>
 * <p>
 * While that goal could be accomplished by taking a pair of TreeMaps
 * and redirecting requests to the appropriate TreeMap (e.g.,
 * containsKey would be directed to the TreeMap that maps values to
 * keys, containsValue would be directed to the TreeMap that maps keys
 * to values), there are problems with that implementation.
 * If the data contained in the TreeMaps is large, the cost of redundant
 * storage becomes significant. The {@link DualTreeBidiMap} and
 * {@link DualHashBidiMap} implementations use this approach.
 * </p>
 * <p>
 * This solution keeps minimizes the data storage by holding data only once.
 * The red-black algorithm is based on {@link java.util.TreeMap}, but has been modified
 * to simultaneously map a tree node by key and by value. This doubles the
 * cost of put operations (but so does using two TreeMaps), and nearly doubles
 * the cost of remove operations (there is a savings in that the lookup of the
 * node to be removed only has to be performed once). And since only one node
 * contains the key and value, storage is significantly less than that
 * required by two TreeMaps.
 * </p>
 * <p>
 * The Map.Entry instances returned by the appropriate methods will
 * not allow setValue() and will throw an
 * UnsupportedOperationException on attempts to call that method.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 *
 * @since 3.0 (previously DoubleOrderedMap v2.0)
 

api document of method doRedBlackDelete(Node<K, V>): 

     * Complicated red-black delete stuff. Based on Sun's TreeMap
     * implementation, though it's barely recognizable anymore.
     *
     * @param deletedNode the node to be deleted
     

dependent classes: 
class `org.apache.commons.collections4.bidimap.TreeBidiMap`:
	dependent fields:
		org.apache.commons.collections4.bidimap.TreeBidiMap.Node<K, V>[] org.apache.commons.collections4.bidimap.TreeBidiMap.rootNode;
	related functions:
		method `private void rotateLeft(Node<K, V>, DataElement)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: 
     * Does a rotate left. standard fare in the world of balanced trees.
     *
     * @param node the node to be rotated
     * @param dataElement  either the {@link DataElement#KEY key}
     *                     or the {@link DataElement#VALUE value}.
     ;
		method `private void doPut(K, V)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: 
     * Puts logic.
     *
     * @param key  the key, always the main map key
     * @param value  the value, always the main map value
     ;
		method `private void doRedBlackInsert(Node<K, V>, DataElement)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: 
     * Complicated red-black insert stuff. Based on Sun's TreeMap
     * implementation, though it's barely recognizable anymore.
     *
     * @param insertedNode the node to be inserted
     * @param dataElement  the KEY or VALUE int
     ;
		method `private void swapPosition(Node<K, V>, Node<K, V>, DataElement)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: 
     * Swaps two nodes (except for their content), taking care of
     * special cases where one is the other's parent ... hey, it
     * happens.
     *
     * @param x one node
     * @param y another node
     * @param dataElement  the KEY or VALUE int
     ;
		method `private Node<K, V> lookup(Object, DataElement)` returns `org.apache.commons.collections4.bidimap.TreeBidiMap.Node<K, V>`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: 
     * Does the actual lookup of a piece of data.
     *
     * @param data the key or value to be looked up
     * @param dataElement  either the {@link DataElement#KEY key}
     *                     or the {@link DataElement#VALUE value}.
     * @return the desired Node, or null if there is no mapping of the
     *         specified data
     ;
		method `private void doRedBlackDeleteFixup(Node<K, V>, DataElement)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: 
     * Complicated red-black delete stuff. Based on Sun's TreeMap
     * implementation, though it's barely recognizable anymore. This
     * rebalances the tree (somewhat, as red-black trees are not
     * perfectly balanced -- perfect balancing takes longer)
     *
     * @param replacementNode the node being replaced
     * @param dataElement  the KEY or VALUE int
     ;
		method `private void insertValue(Node<K, V>)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: 
     * Inserts a node by its value.
     *
     * @param newNode the node to be inserted
     *
     * @throws IllegalArgumentException if the node already exists
     *                                     in the value mapping
     ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
