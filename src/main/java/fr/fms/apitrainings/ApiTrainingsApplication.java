package fr.fms.apitrainings;

import fr.fms.apitrainings.dao.*;
import fr.fms.apitrainings.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiTrainingsApplication implements CommandLineRunner {

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiTrainingsApplication.class, args);
	}

	@Override
	public void run(String... args) throws  Exception {
		generatedData();
	}

	public void generatedData() {
		List<Training> trainingsList = new ArrayList<>();

		Category informatique = new Category("Informatique", trainingsList);
		Category cuisine = new Category("Cuisine", trainingsList);
		Category anglais = new Category("Langues étrangères", trainingsList);
		Category finance = new Category("Finance et comptabilité", trainingsList);

		categoryRepository.save(informatique);
		categoryRepository.save(cuisine);
		categoryRepository.save(anglais);
		categoryRepository.save(finance);

		trainingRepository.save(new Training(null, "Java", "Formation Java", 150, 1, "cuisine_chinoise",informatique));
		trainingRepository.save(new Training(null, "C", "Formation C", 100, 1, "cuisine_chinoise", informatique));
		trainingRepository.save(new Training(null, "Javascript", "Formation Javascript", 120, 1, "cuisine_chinoise", informatique));
		trainingRepository.save(new Training(null, "Python", "Formation Python", 300, 1, "cuisine_chinoise", informatique));
		trainingRepository.save(new Training(null, "NodeJS", "Formation NodeJS", 175, 1, "cuisine_chinoise", informatique));

		trainingRepository.save(new Training(null, "Bases", "Les bases de la cuisine", 90, 1, "cuisine_chinoise", cuisine));
		trainingRepository.save(new Training(null, "Cuisine du monde", "Les cuisines du monde", 300, 1, "cuisine_chinoise", cuisine));
		trainingRepository.save(new Training(null, "Pâtisserie", "Les bases de la pâtisserie", 310, 1, "cuisine_chinoise", cuisine));
		trainingRepository.save(new Training(null, "Vegan", "Apprendre la cuisine vegan", 45, 1, "cuisine_chinoise", cuisine));
		trainingRepository.save(new Training(null, "Santé", " Élaboration de plats équilibrés", 155, 1, "cuisine_chinoise", cuisine));

		trainingRepository.save(new Training(null, "Anglais", "Formation à l'anglais", 150, 1, "cuisine_chinoise", anglais));
		trainingRepository.save(new Training(null, "Espagnol", "Formation à l'espagnol", 100, 1, "cuisine_chinoise", anglais));
		trainingRepository.save(new Training(null, "Français", "Formation au français", 120, 1, "cuisine_chinoise", anglais));
		trainingRepository.save(new Training(null, "Allemand", "Formation à l'allemand", 300, 1, "cuisine_chinoise", anglais));
		trainingRepository.save(new Training(null, "Mandarin", "Formation au mandarin", 175, 1, "cuisine_chinoise", anglais));

		trainingRepository.save(new Training(null, "Comptabilité", "Les bases de la comptabilité", 150, 1, "cuisine_chinoise", finance));
		trainingRepository.save(new Training(null, "Analyse", "Formation aux analyses financières", 100, 1, "cuisine_chinoise", finance));
		trainingRepository.save(new Training(null, "Gestion", "Formation au gestion budgétaire", 120, 1, "cuisine_chinoise", finance));
		trainingRepository.save(new Training(null, "Fiscalité", "Fiscalité des entreprises", 300, 1, "cuisine_chinoise", finance));
		trainingRepository.save(new Training(null, "Investissement", "Investissement et gestion de portefeuille", 175, 1, "cuisine_chinoise", finance));

		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		roleRepository.save(admin);
		roleRepository.save(user);

		List<Role> arthurRole = new ArrayList<>();
		List<Role> ambreRole = new ArrayList<>();
		arthurRole.add(admin);
		arthurRole.add(user);
		ambreRole.add(user);

		User arthur = new User("arthur", "123", true);
		User ambre = new User("ambre", "123", true);
		userRepository.save(arthur);
		userRepository.save(ambre);

		UserRole arthurAdmin = new UserRole(arthur, admin);
		UserRole arthurUser = new UserRole(arthur, user);
		UserRole ambreUser = new UserRole(ambre, user);
		userRoleRepository.save(arthurAdmin);
		userRoleRepository.save(arthurUser);
		userRoleRepository.save(ambreUser);

	}

}
