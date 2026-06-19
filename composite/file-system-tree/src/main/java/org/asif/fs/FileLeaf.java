package org.asif.fs;

/**
 * The <em>leaf</em>: a single file. It has no children, so its size is simply
 * its own size and its tree is a single line.
 */
public final class FileLeaf implements FileSystemNode {
    private final String name;
    private final long sizeInBytes;

    public FileLeaf(final String name, final long sizeInBytes) {
        this.name = name;
        this.sizeInBytes = sizeInBytes;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public long sizeInBytes() {
        return sizeInBytes;
    }

    @Override
    public String tree(final String indent) {
        return indent + name + " (" + sizeInBytes + " B)\n";
    }
}
