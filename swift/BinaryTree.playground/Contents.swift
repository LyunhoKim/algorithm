public class BinaryNode<Element> {
  public var value: Element
  public var leftChild: BinaryNode?
  public var rightChild: BinaryNode?
  
  public init(value: Element) {
    self.value = value
  }
}

var tree: BinaryNode<Int> = {
  let zero = BinaryNode(value: 0)
  let one = BinaryNode(value: 1)
  let five = BinaryNode(value: 5)
  let seven = BinaryNode(value: 7)
  let eight = BinaryNode(value: 8)
  let nine = BinaryNode(value: 9)
  
  seven.leftChild = one
  one.leftChild = zero
  one.rightChild = five
  seven.rightChild = nine
  nine.leftChild = eight
  
  return seven
}()

print(tree)

tree.traversalInOrder{ print($0) }

extension BinaryNode: CustomStringConvertible {
  public var description: String {
    diagram(for: self)
  }
  
  private func diagram(for node: BinaryNode?, _ top: String = "", _ root: String = "", _ bottom: String = "") -> String {
    guard let node = node else {
      return root + "nil\n"
    }
    if node.leftChild == nil && node.rightChild == nil {
      return root + "\(node.value)\n"
    }
    return
      diagram(for: node.rightChild, top + " ", top + "┌──", top + "| ")
      + root + "\(node.value)\n"
      + diagram(for: node.leftChild, bottom + "| ", bottom + "└──", bottom + " ")
  }
}


// inorder
extension BinaryNode {
  public func traversalInOrder(visit: (Element) -> Void) {
    leftChild?.traversalInOrder(visit: visit)
    visit(value)
    rightChild?.traversalInOrder(visit: visit)
  }
}

// preorder
extension BinaryNode {
  public func traversalPreOrder(visit: (Element) -> Void) {
    leftChild?.traversalPreOrder(visit: visit)
    visit(value)
    rightChild?.traversalPreOrder(visit: visit)
  }
}

// postorder
extension BinaryNode {
  public func traversalPostOrder(visit: (Element) -> Void) {
    leftChild?.traversalPostOrder(visit: visit)
    visit(value)
    rightChild?.traversalPostOrder(visit: visit)
  }
}
