# XPath Tutorial: Navigating XML Documents with Namespaces

A comprehensive guide to using XPath (XML Path Language) for querying XML documents, featuring practical examples with a restaurant XML document that includes namespaces, structured data, and complex hierarchies.

## Table of Contents

- [What is XPath?](#what-is-xpath)
- [Installation & Setup](#installation--setup)
- [Basic XPath Syntax](#basic-xpath-syntax)
- [Handling Namespaces](#handling-namespaces)
- [XPath Examples](#xpath-examples)
  - [Selecting Nodes](#selecting-nodes)
  - [Using Predicates](#using-predicates)
  - [Accessing Attributes](#accessing-attributes)
  - [Using Functions](#using-functions)
  - [Combining Expressions](#combining-expressions)
- [Common XPath Axes](#common-xpath-axes)
- [Best Practices](#best-practices)
- [Resources](#resources)

## What is XPath?

XPath is a powerful query language for selecting nodes or computing values from XML documents. It provides capabilities for:

- **Selecting elements, attributes, or text content**
- **Filtering nodes with conditional expressions**
- **Navigating XML tree structures using paths and axes**
- **Performing operations like counting and string manipulation**

XPath is widely used in web scraping, XML parsing, XSLT transformations, and JavaScript's `document.evaluate()` method.

## Installation & Setup

### Python (using lxml)

```bash
pip install lxml
```

### JavaScript (Browser)

```javascript
// Built-in support with document.evaluate()
// No installation required
```

### Namespace Setup

When working with namespaced XML, define namespace mappings:

**Python (lxml):**

```python
nsmap = {'ns': 'http://example.com/restaurants'}
```

**JavaScript:**

```javascript
const resolver = {
    lookupNamespaceURI: prefix => 
        prefix === 'ns' ? 'http://example.com/restaurants' : null
};
```

## Basic XPath Syntax

| Syntax | Description | Example |
|--------|-------------|---------|
| `/` | Path separator for child nodes | `/restaurants/restaurant` |
| `//` | Select nodes anywhere in document | `//menu/item` |
| `[...]` | Predicates for filtering | `[rating > 4.5]` |
| `@` | Select attributes | `@id` |
| `*` | Wildcard for any element | `/*` |
| `text()` | Select text content | `name/text()` |

## Handling Namespaces

The tutorial uses XML with a default namespace: `http://example.com/restaurants`

### With Namespace Support

```xpath
/ns:restaurants/ns:restaurant/ns:name
```

### Without Namespace Support (Alternative)

```xpath
/*[local-name()='restaurants']/*[local-name()='restaurant']/*[local-name()='name']
```

## XPath Examples

### Selecting Nodes

#### Get all restaurant names

```xpath
/ns:restaurants/ns:restaurant/ns:name
```

**Result:** Urban Eats, Coastal Delights, Mountain Grill

#### Get all menu items

```xpath
//ns:menu/ns:item
```

**Result:** All menu items across all restaurants

#### Get first restaurant's contact

```xpath
/ns:restaurants/ns:restaurant[1]/ns:contact
```

**Result:** +254700123456

### Using Predicates

#### Restaurants with rating > 4.5

```xpath
/ns:restaurants/ns:restaurant[ns:rating > 4.5]
```

**Result:** Coastal Delights (rating 4.8)

#### Vegan menu items

```xpath
//ns:menu/ns:item[ns:category = 'Vegan']
```

**Result:** Avocado Toast, Fruit Salad, Vegan Wrap, Vegetable Stir Fry, Vegetable Pasta

#### Reviews from specific date

```xpath
//ns:review[ns:date = '2025-06-05']
```

### Accessing Attributes

#### Get all restaurant IDs

```xpath
/ns:restaurants/ns:restaurant/@id
```

#### Items with specific currency

```xpath
//ns:item[ns:price/@currency = 'KES']
```

### Using Functions

#### Count restaurants

```xpath
count(/ns:restaurants/ns:restaurant)
```

**Result:** 3

#### Find restaurants with 'Grill' in name

```xpath
/ns:restaurants/ns:restaurant[contains(ns:name, 'Grill')]/ns:name
```

**Result:** Mountain Grill

#### Sum all menu item prices

```xpath
sum(//ns:item/ns:price)
```

**Result:** 6950.00

### Combining Expressions

#### Vegan items from Urban Eats

```xpath
/ns:restaurants/ns:restaurant[ns:name = 'Urban Eats']/ns:menu/ns:item[ns:category = 'Vegan']
```

#### Restaurants open after 22:00 on Monday

```xpath
/ns:restaurants/ns:restaurant[ns:operatingHours/ns:day[@name = 'Monday']/ns:close > '22:00:00']
```

## Common XPath Axes

### Descendant Axis

```xpath
/ns:restaurants/ns:restaurant[1]/descendant::*
```

Selects all descendants of the first restaurant.

### Parent Axis

```xpath
//ns:review[@id = '3f2504e0-4f89-11d3-9a0c-0305e82c3301']/parent::*
```

Selects the parent of a specific review.

### Following-Sibling Axis

```xpath
//ns:item[@id = 'e7d9f952-cbe8-4c19-b48f-1e5c1a680a5a']/following-sibling::ns:item
```

Selects sibling items after a specific menu item.

## Best Practices

### 🎯 Namespace Handling

- Always use proper namespace prefixes with namespaced XML
- Use `local-name()` as fallback when namespace support is unavailable
- Test namespace mappings before running complex queries

### ⚡ Performance

- Use specific paths instead of `//` when possible
- Avoid overly complex predicates in large documents
- Consider indexing for frequently queried elements

### 🧪 Testing

- Use XPath testing tools during development
- Validate queries with sample data
- Handle missing nodes gracefully in your code

### 🛡️ Error Handling

- Check for null/empty results
- Validate attribute existence before accessing
- Use defensive programming with optional elements

## Usage Examples

### Python with lxml

```python
from lxml import etree

# Parse XML
tree = etree.parse('restaurants.xml')
nsmap = {'ns': 'http://example.com/restaurants'}

# Execute XPath
restaurants = tree.xpath('/ns:restaurants/ns:restaurant/ns:name', namespaces=nsmap)
for name in restaurants:
    print(name.text)
```

### JavaScript

```javascript
// Assuming XML is loaded in xmlDoc
const xpath = "/ns:restaurants/ns:restaurant/ns:name";
const resolver = prefix => prefix === 'ns' ? 'http://example.com/restaurants' : null;
const result = document.evaluate(xpath, xmlDoc, resolver, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE);

for (let i = 0; i < result.snapshotLength; i++) {
    console.log(result.snapshotItem(i).textContent);
}
```

### Java

```java
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

// Parse XML document
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
factory.setNamespaceAware(true);
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse("restaurants.xml");

// Create XPath
XPathFactory xPathFactory = XPathFactory.newInstance();
XPath xpath = xPathFactory.newXPath();

// Set namespace context
xpath.setNamespaceContext(new NamespaceContext() {
    public String getNamespaceURI(String prefix) {
        if ("ns".equals(prefix)) {
            return "http://example.com/restaurants";
        }
        return null;
    }
    
    public String getPrefix(String namespaceURI) {
        return null;
    }
    
    public Iterator getPrefixes(String namespaceURI) {
        return null;
    }
});

// Execute XPath query
String expression = "/ns:restaurants/ns:restaurant/ns:name";
XPathExpression xPathExpression = xpath.compile(expression);
NodeList nodes = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);

// Process results
for (int i = 0; i < nodes.getLength(); i++) {
    Node node = nodes.item(i);
    System.out.println(node.getTextContent());
}
```

## Resources

- **[W3C XPath 3.1 Specification](https://www.w3.org/TR/xpath-31/)** - Official specification
- **[Online XPath Tester](https://www.freeformatter.com/xpath-tester.html)** - Test XPath expressions
- **[XPath Cheat Sheet](https://devhints.io/xpath)** - Quick reference guide
- **[MDN XPath Documentation](https://developer.mozilla.org/en-US/docs/Web/XPath)** - Browser XPath reference

---

## Contributing

Feel free to contribute improvements to this tutorial:

1. Fork the repository
2. Create a feature branch
3. Add examples or clarifications
4. Submit a pull request

## License

This tutorial is provided under the MIT License. See LICENSE file for details.

---

*Happy XPath querying! 🚀*
