#encoding:utf-8
class Node:
    def __init__(self, value):
        self._value = value
        self._left = None
        self._right = None

class BST:
    _root=None
    def __init__(self,value):
        self._root=Node(value)

    def add(self,value):
        self.inner_add(value,self._root)

    def find(self,value):
        return self.inner_find(value,self._root)

    def delete(self,value):
        self.inner_delete(value,self._root)

    def serialize(self):
        return self.inner_serialize(self._root)

    def desirialize(self,list):
        self.inner_desirialize(self._root,list,0,len(list)-1)

    def inner_add(self, value, root):
        if root is None:
            return Node(value)
        elif root._value>value:
            root._left = self.inner_add(value, root._left)
        elif root._value<value:
            root._right = self.inner_add(value, root._right)
        return root

    def inner_find(self, value, root):
        if root is None:
            return False
        elif root._value>value:
            return self.inner_find(value, root._left)
        elif root._value<value:
            return self.inner_find(value, root._right)
        else:
            return True

    def inner_delete(self, value, root):
        if root is None:
            return None
        elif root._value>value:
            root._left = self.inner_delete(value, root._left)
        elif root._value<value:
            root._right = self.inner_delete(value, root._right)
        else:
            if root._left is None:
                return root._right
            elif root._right is None:
                return root._left
            else:
                p = root._left
                if p._right is None:
                    root._value = p._value
                    root._left = p._left
                else:
                    while not p._right._right is None:
                        p = p._right
                    root._value = p._right._value
                    p._right = p._right._left
        return root

    def inner_serialize(self,root):
        list = []
        if not root is None:
            list.append(root._value)
            list.extend(self.inner_serialize(root._left))
            list.extend(self.inner_serialize(root._right))
        return list

    def inner_desirialize(self,root,list,start,end):
        if start<=end:
            root._value = list[start]
            i = start +1
            while i<end and list[i]<root._value:
                i = i+1
            self.inner_desirialize(root._left,list,start+1,i-1)
            self.inner_desirialize(root._right,list,i,end)

if __name__ == '__main__':
    tree=BST(10)
    tree.add(15)
    tree.add(13)
    tree.add(14)
    tree.add(17)
    tree.add(9)

    print(tree.serialize())
    tree.desirialize(tree.serialize())
    print(tree.serialize())