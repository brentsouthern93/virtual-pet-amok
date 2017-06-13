
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VirtualPetShelter {
	// instance variables
	private int litterBox = 50;
	
	// constructor for Map of pets in shelter
	Map<String, VirtualPet> shelterPets = new HashMap<String, VirtualPet>();

	//returns status value of litterbox
	public int getLitterBox() {
		return litterBox;
	}
    
	//clean litter box returns a 0 status to litter box
	public void cleanLitterBox() {
		litterBox = 0;
	}
    //maintain all robotic pets
	public void maintainAllRobo() {
		for (VirtualPet pet : pets()) {
			if (pet instanceof RoboticPet) {
				((RoboticPet) pet).oilPet();
				((RoboticPet) pet).recharge();
			}
		}
	}
    //clean cages of all organic dogs in shelter
	public void cleanDogCages() {
		for (VirtualPet pet : pets()) {
			if (pet instanceof OrgDog) {
				((OrgDog) pet).cleanCage();
			}
		}
	}
    //walks all dogs in shelter
	public void walkDogs() {
		for (VirtualPet pet : pets()) {
			if (pet instanceof Dog) {
				((Dog) pet).walk();
			}
		}

	}

	//intake a virtual pet in to the shelter
	public void intake(VirtualPet pet) {
		shelterPets.put(pet.getName(), (VirtualPet) pet);
	}

	// passes in type Robotic pet type.  casting up as Virtual pet to put in shelter pet map
	public void intake(RoboticPet pet) {
		shelterPets.put(pet.getName(), (VirtualPet) pet);
	}
	//passes in type Organic pet type.  casting up as Virtual pet to put in shelter pet map
	public void intake(OrganicPet pet) {
		shelterPets.put(pet.getName(), (VirtualPet) pet);
	}

	//feeds all organic pets
	public void feedAllOrganic() {
		for (VirtualPet pet : pets()) {
			if (pet instanceof OrganicPet) {
				((OrganicPet) pet).feed();
			}
		}
		litterBox += 10;
	}

	//Waters all organic pets
	public void waterAllOrganic() {
		for (VirtualPet pet : pets()) {
			if (pet instanceof OrganicPet) {
				((OrganicPet) pet).water();
			}
		}
		litterBox += 10;
	}

	// Tick all pets
	void tickAllPets() {
		for (VirtualPet pet : pets()) {
			if (pet instanceof OrgCat) {
				litterBox += pet.tick();
			} else if (pet instanceof OrganicPet) {
				pet.tick();
			}
		}
	}

	// Method returning collection of all pets in the shelter
	public Collection<VirtualPet> pets() {
		return shelterPets.values();
	}

	// Method to return a pet from the map by name
	public VirtualPet getPet(String name) {
		return shelterPets.get(name);

	}

	// Method to play with one pet
	public void playOne(VirtualPet player) {
		player.play();
	}

	// Method to adopt a pet
	public void adoptPet(VirtualPet pet) {
		shelterPets.remove(pet.getName());
	}
}