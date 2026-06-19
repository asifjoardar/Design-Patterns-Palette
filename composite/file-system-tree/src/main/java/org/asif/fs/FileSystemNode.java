package org.asif.fs;

/**
 * The <em>component</em>. It is the common interface for both single files and
 * whole directories, so client code can treat a leaf and a tree of nodes
 * exactly the same way — ask any node for its size or print it, no matter how
 * deeply it nests.
 */
public interface FileSystemNode {

    /**
     * @return the node's own name (no path)
     */
    String name();

    /**
     * @return the total size of this node in bytes; for a directory this is the
     *         sum of everything it contains, computed recursively
     */
    long sizeInBytes();

    /**
     * Renders this node (and its children, if any) as an indented tree.
     *
     * @param indent the prefix to prepend to this node's line
     * @return a multi-line string ending with a newline
     */
    String tree(String indent);
}
