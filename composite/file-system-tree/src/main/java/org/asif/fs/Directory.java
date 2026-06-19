package org.asif.fs;

import java.util.ArrayList;
import java.util.List;

/**
 * The <em>composite</em>: a directory that holds other nodes — files or further
 * directories. Crucially its children are themselves {@link FileSystemNode}s, so
 * {@link #sizeInBytes()} and {@link #tree(String)} just recurse over them
 * without ever caring whether a child is a file or another directory.
 */
public final class Directory implements FileSystemNode {
    private final String name;
    private final List<FileSystemNode> children = new ArrayList<>();

    public Directory(final String name) {
        this.name = name;
    }

    /**
     * Adds a child node and returns this directory, so calls can be chained.
     *
     * @param node the file or sub-directory to add
     * @return this directory
     */
    public Directory add(final FileSystemNode node) {
        children.add(node);
        return this;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public long sizeInBytes() {
        long total = 0;
        for (final FileSystemNode child : children) {
            total += child.sizeInBytes();
        }
        return total;
    }

    @Override
    public String tree(final String indent) {
        final StringBuilder builder = new StringBuilder();
        builder.append(indent).append(name).append("/\n");
        for (final FileSystemNode child : children) {
            builder.append(child.tree(indent + "  "));
        }
        return builder.toString();
    }
}
