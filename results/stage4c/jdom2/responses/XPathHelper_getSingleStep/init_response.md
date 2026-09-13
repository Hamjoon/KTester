```java
package org.jdom2.xpath;

import org.jdom2.NamespaceAware;
import org.jdom2.Content;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Parent;
import org.jdom2.filter.Filters;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class XPathHelper_getSingleStep_Test {

    @Mock
    NamespaceAware mockNamespaceAware;

    @Mock
    StringBuilder mockStringBuilder;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize resources needed for all tests, if any
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize resources needed before each test
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup resources after all tests, if any
    }

    @Test
    void testGetSingleStepForContent() {
        // given
        Content mockContent = mock(Content.class);
        Parent mockParent = mock(Parent.class);
        when(mockContent.getParent()).thenReturn(mockParent);
        List<Content> siblings = new ArrayList<>();
        when(mockParent.getContent(Filters.text())).thenReturn(siblings);

        // when
        StringBuilder result = XPathHelper.getSingleStep(mockContent, mockStringBuilder);

        // then
        assertNotNull(result);
    }

    @Test
    void testGetSingleStepForAttribute() {
        // given
        Attribute mockAttribute = mock(Attribute.class);
        when(mockAttribute.getNamespace()).thenReturn(Namespace.NO_NAMESPACE);
        when(mockAttribute.getName()).thenReturn("attrName");

        // when
        StringBuilder result = XPathHelper.getSingleStep(mockAttribute, mockStringBuilder);

        // then
        assertNotNull(result);
    }
}
```