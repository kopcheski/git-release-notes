package com.kopcheski.labs.git.release.notes.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReleaseNotesParserTest {

	ReleaseNotesParser parser;
	List<String> textBlocks;

	int charCount;

	@BeforeEach
	void setUp() {
		parser = new ReleaseNotesParser(Paths.get("src/test/resources/2.37.0.txt"));
		charCount = parser.originalContent().length();
		textBlocks = parser.parse();
	}

	@Test
	void testFileContentSplitByMaxCharCount() {
		int expectedBlocksCount = (charCount / ReleaseNotesParser.MAX_BLOCK_SIZE) + 1;
		assertEquals(expectedBlocksCount, textBlocks.size());
	}

	@Test
	void testFileContentSplitPreservesOriginalContent() {
		final String actualContent = textBlocks.stream()
				.reduce("", String::concat);

		assertEquals(parser.originalContent(), actualContent);
	}

}
