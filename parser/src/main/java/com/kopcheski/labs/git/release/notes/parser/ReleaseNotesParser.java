package com.kopcheski.labs.git.release.notes.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReleaseNotesParser {

	static final int MAX_BLOCK_SIZE = 280;
	private final String content;

	public ReleaseNotesParser(Path path) {
		try {
			this.content = Files.readString(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> parse() {
		var blocks = new ArrayList<String>();
		int startIndex = 0;
		for (;
			 startIndex + MAX_BLOCK_SIZE < content.length();
			 startIndex += MAX_BLOCK_SIZE) {
			blocks.add(this.content.substring(startIndex, startIndex + MAX_BLOCK_SIZE));
		}
		blocks.add(this.content.substring(startIndex));
		return blocks;
	}

	String originalContent() {
		return this.content;
	}
}
