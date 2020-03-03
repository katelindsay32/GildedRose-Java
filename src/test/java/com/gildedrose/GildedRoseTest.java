package com.gildedrose;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

	@Test
	public void CompareOutput() throws IOException {
		String newFileName = "newOutput.txt";
		String originalFileName = "originalOutput.txt";

		executeSimulation(20, newFileName);

		Scanner newFile = new Scanner(new File(newFileName));
		Scanner originalFile = new Scanner(new File(originalFileName));

		while (newFile.hasNextLine() || originalFile.hasNextLine()) {
			assertEquals(newFile.nextLine(), originalFile.nextLine());
		}
	}

	private static void executeSimulation(int days, String fileName) throws IOException {
		Item[] items = SetupTestItems();
		FileWriter fileWriter = new FileWriter(fileName);
		GildedRose app = new GildedRose(items);
		for (int i = 0; i < days; i++) {
			fileWriter.write("-------- day " + i + " --------" + "\n");
			fileWriter.write("name, sellIn, quality" + "\n");
			for (Item item : app.items) {
				fileWriter.write(item + "\n");
			}
			app.updateQuality();
		}
		fileWriter.close();
	}

	private static Item[] SetupTestItems() {
		return new Item[]{
				new Item("+5 Dexterity Vest", 10, 20), //
				new Item("Aged Brie", 2, 0), //
				new Item("Elixir of the Mongoose", 5, 7), //
				new Item("Sulfuras, Hand of Ragnaros", 10, 80), //
				new Item("Sulfuras, Hand of Ragnaros", -1, 80),
				new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
				new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
				new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
				// this conjured item does not work properly yet
				new Item("Conjured Mana Cake", 3, 6)};
	}
}
