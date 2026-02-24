Problem Statement: Dynamic Skyline View of a Binary Tree
A city skyline where buildings are arranged in a binary tree structure (each building can have
at most two sub-buildings).
From a specific side view, only some buildings are visible because others are blocked.
You are given a binary tree. Each node represents a building with a height value.
Your task is to compute the Right Skyline View of the city:
The Right Skyline View contains exactly one building per level:
the rightmost visible building when the city is viewed from the right side.
You are also given a list of queries:
• Each query removes (demolishes) one subtree.
• After each removal, you must output the new Right Skyline View of the tree.
Example 1:
Tree 1:
 1
 / \
 2 3
 / \ \
 4 5 6
 \
 7
Input 1:
Queries[] = {5, 3, 2}
Output 1:
After removing subtree rooted at 5: [1, 3, 6]
After removing subtree rooted at 3: [1, 2, 4]
After removing subtree rooted at 2: [1, 3, 6]
Example 2:
Tree 2:
1
\
 2
 \
 3
 \
 4
Input 2 :
Queries = {3, 2}
Output 2:
After removing subtree rooted at 3: [1, 2]
After removing subtree rooted at 2: [1]
Constraints :
• 1 ≤ N ≤ 10^5 (number of nodes)
• 1 ≤ Q ≤ 10^5 (number of queries)
• 1 ≤ Node.val ≤ 10^9 (unique values)
