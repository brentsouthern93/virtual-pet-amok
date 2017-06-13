

import java.util.Scanner;

public class VirtualPetApp {

	public static void main(String[] args) {

		VirtualPetShelter shelter = new VirtualPetShelter();
		Scanner input = new Scanner(System.in);

		// Using OrganicPet interface to create OrgDog object. Interfaces can't instantiate
		OrganicPet Buddy = new OrgDog("Buddy", "Retreiver");
		shelter.intake(Buddy);
		// Using RoboticPet interface to create Robodog object
		RoboticPet Jax = new RoboDog("Jax", "A robo dog");
		shelter.intake(Jax);
		// Using OrganicPet interface to create OrgCat object
		OrganicPet Meowington = new OrgCat("Meowington", "A delightful cat");
		shelter.intake(Meowington);
		// Using RoboticPet interface to create Robocat object
		RoboticPet Catinator = new RoboCat("Catinator", "A robo cat");
		shelter.intake(Catinator);

		boolean quit = false;

		do {
			//decrease organic cats health when litter box stat is >= 100
			if (shelter.getLitterBox() >= 100) {
				writeLine("The litter box is dirty and is decreasing the health of all cats in the shelter.");
				for (VirtualPet pet : shelter.pets()) {
					if (pet instanceof OrgCat) {
						((OrgCat) pet).decreaseHealth();
					}
				}
			}
			//decrease an organic dogs health if cage messiness stat is >=100
			for (VirtualPet pet : shelter.pets()) {
				if (pet instanceof OrgDog) {
					if (((OrgDog) pet).getCageMessiness() >= 100) {
						writeLine("The organic dog " + pet.getName()
								+ "'s cage needs cleaning and is decreasing in health.");
						((OrgDog) pet).decreaseHealth();
					}
				}
			}
            
			// Display Virtual Pet shelter Status and interactive menu
			writeLine("\nThank you for helping out at the Virtual Shelter!");
			writeLine("\nThis is the status of your pets: ");
			writeLine("\nName\t|Mood\t|Health\t|Hunger\t|Thirst\t|OilLvl\t|CageMess");
			writeLine("--------|-------|-------|-------|-------|-------|-------");

			for (VirtualPet currentPet : shelter.pets()) {
				System.out.print(currentPet.getName() + "\t|" + ((VirtualPet) currentPet).getMood() + "\t|"
						+ ((VirtualPet) currentPet).getHealth() + "\t|");
				if (currentPet instanceof OrganicPet) {
					System.out.print(((OrganicPet) currentPet).getHunger() + "\t|"
							+ ((OrganicPet) currentPet).getThirst() + "\t|N/A\t|");
				} else if (currentPet instanceof RoboticPet) {
					System.out.print("N/A\t|N/A\t|" + ((RoboticPet) currentPet).getOilLevel() + "\t|");
				}
				if (currentPet instanceof OrgDog) {
					System.out.print("" + ((OrgDog) currentPet).getCageMessiness() + "\t|");
				} else {
					System.out.print("N/A\t|");
				}
				System.out.print("\n");

			}

			writeLine("\n|Shelter litterbox|\n" +"\t" + shelter.getLitterBox());
			writeLine("\nWhat would you like to do next?");
			writeLine(
					"\n1.Feed the organic pets \n2.Water the organic pets \n3.Play with a pet \n4.Adopt a pet \n5.Admit a pet \n6.Clean Cages \n7.Clean Litterbox \n8.Walk Dogs \n9.Maintain all RoboPets \n10.Do nothing \n11.Quit");
			String response = input.nextLine();

			switch (response) {
			case "1":
				// Feed all shelter pets
				shelter.feedAllOrganic();
				writeLine("You fed all the organic pets!");
				break;
			case "2":
				// Water all shelter pets
				shelter.waterAllOrganic();
				writeLine("You watered all the organic pets!");
				break;
			case "3":
				// Play with a single pet
				writeLine("You selected to play with a pet. Please choose one:\n");
				displayPets(shelter);
				writeLine("\nWhich pet would you like to play with?");
				String petName = input.nextLine();
				shelter.playOne(shelter.getPet(petName));
				writeLine("OK, you play with " + shelter.getPet(petName) + ". ");
				break;
			case "4":
				// Adopt a pet
				writeLine("You selected to adopt a pet. Please choose one:\n");
				displayPets(shelter);
				writeLine("\nWhich pet would you like to adopt");
				String nameToAdopt = input.nextLine();
				VirtualPet x = shelter.getPet(nameToAdopt);
				shelter.adoptPet(x);
				writeLine("You adopted " + nameToAdopt + ". Please take good care of him/her!");
				break;
			case "5":
				// Intake a pet
				writeLine("You have a pet for us to intake, is it organic or robotic?");
				String response2 = input.next();
				if (response2.equalsIgnoreCase("organic")) {
					writeLine("Great! is your organic pet a cat or a dog?");
					String response3 = input.next();
					input.nextLine();

					if (response3.equalsIgnoreCase("dog")) {
						writeLine("Great! What is the dog's name?");
						String name = input.nextLine();

						writeLine("Great! What is a short description of the dog?");
						String description = input.nextLine();

						OrganicPet pet = new OrgDog(name, description);
						shelter.intake(pet);
						writeLine("Thanks! We'll take good care of " + pet.getName() + ".");
					} else if (response3.equalsIgnoreCase("cat")) {
						writeLine("Great! What is the cat's name?");
						String name = input.nextLine();

						writeLine("Great! What is a short description of the cat?");
						String description = input.nextLine();

						OrganicPet pet = new OrgCat(name, description);
						shelter.intake(pet);
						writeLine("Thanks! We'll take good care of " + pet.getName() + ".");
					}

				} else if (response2.equalsIgnoreCase("robotic")) {
					writeLine("Great! is your robotic pet a cat or a dog?");
					String response3 = input.next();
					input.nextLine();

					if (response3.equalsIgnoreCase("dog")) {
						writeLine("What is the dog's name?");
						String name = input.nextLine();
						writeLine("Please give a short description of the dog?");
						String description = input.nextLine();

						RoboticPet pet = new RoboDog(name, description);
						shelter.intake(pet);
						writeLine("Thanks! We'll take good care of " + pet.getName() + ".");

					} else if (response3.equalsIgnoreCase("cat")) {
						writeLine("What is the cat's name?");
						String name = input.nextLine();
						writeLine("Please give a short description of the cat?");
						String description = input.nextLine();

						RoboticPet pet = new RoboCat(name, description);
						shelter.intake(pet);
						writeLine("Thanks! We'll take good care of " + pet.getName() + ".");
					}
				}
				break;
			case "6":
				// Clean organic dog cages
				shelter.cleanDogCages();
				writeLine("You cleaned all the dog cages!");
				break;
			case "7":
				// clean organic cat litter box
				shelter.cleanLitterBox();
				writeLine("You cleaned the litter box!");
				break;
			case "8":
				// Walk all the dogs in the shelter
				shelter.walkDogs();
				writeLine("You walked all the dogs!");
				break;
			case "9":
				// maintain all robotic pets
				shelter.maintainAllRobo();
				writeLine("You maintained all robotic pets!");
				break;
			case "10":
				// tick
				break;
			case "11":
				writeLine("You have exited the Virtual Pet Shelter, Goodbye");
				System.exit(0);
			default:
				writeLine("Invalid Selection. Try again.");
				break;

			}
			shelter.tickAllPets();

		} while (!quit);
		input.close();

	}

	private static void displayPets(VirtualPetShelter shelter) {
		for (VirtualPet currentPet : shelter.pets()) {
			writeLine("" + currentPet);
		}
	}

	public static void writeLine(String message) {
		System.out.println(message);
	}
}