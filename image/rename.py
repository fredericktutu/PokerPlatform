from pathlib import Path

if __name__ == "__main__":
    for image_file in Path("./").glob("*.png"):
        if image_file.name.startswith("clubs"):
            image_file.rename("card_0_" + image_file.name[len("clubs"):])
        elif image_file.name.startswith("diamonds"):
            image_file.rename("card_1_" + image_file.name[len("diamonds"):])
        elif image_file.name.startswith("hearts"):
            image_file.rename("card_2_" + image_file.name[len("hearts"):])
        elif image_file.name.startswith("spades"):
            image_file.rename("card_3_" + image_file.name[len("spades"):])
        elif image_file.name.startswith("Joker"):
            image_file.rename("card_4_" + image_file.name[len("Joker"):])