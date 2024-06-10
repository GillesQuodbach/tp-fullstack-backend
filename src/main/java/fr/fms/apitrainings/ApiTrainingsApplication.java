package fr.fms.apitrainings;

import fr.fms.apitrainings.dao.*;
import fr.fms.apitrainings.entities.*;
import fr.fms.apitrainings.security.entities.AppRole;
import fr.fms.apitrainings.security.entities.AppUser;
import fr.fms.apitrainings.security.service.AccountService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the API Trainings application.
 */
@SpringBootApplication
public class ApiTrainingsApplication implements CommandLineRunner {
	@Value("${app.home}")
	private String userHome;

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder getbCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	/**
	 * The entry point of the application.
	 *
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiTrainingsApplication.class, args);
	}

	/**
	 * Run method implemented from CommandLineRunner interface.
	 *
	 * @param args the command line arguments.
	 * @throws Exception if an error occurs.
	 */
	@Override
	public void run(String... args) throws Exception {
		generatedData();
		generateUsersRoles();
	}

	/**
	 * Method to generate sample data.
	 */
	public void generatedData() {

		List<Training> trainingsList = new ArrayList<>();
		List<Order> ordersList = new ArrayList<>();
		List<OrderItem> orderItem = new ArrayList<>();

		Category informatique = new Category("Informatique", trainingsList);
		Category cuisine = new Category("Cuisine", trainingsList);
		Category anglais = new Category("Langues étrangères", trainingsList);
		Category finance = new Category("Finance et comptabilité", trainingsList);

		categoryRepository.save(informatique);
		categoryRepository.save(cuisine);
		categoryRepository.save(anglais);
		categoryRepository.save(finance);
		
		trainingRepository.save(new Training(null, "Java", "Formation Java", 150, 1, 5,"java.jpg", false, informatique));
		trainingRepository.save(new Training(null, "C", "Formation C", 100, 1,2, "c.jpg", true, informatique));
		trainingRepository.save(new Training(null, "Javascript", "Formation Javascript", 120, 1,3, "JS.jpg",true, informatique));
		trainingRepository.save(new Training(null, "Python", "Formation Python", 300, 1,40, "python.jpg",true, informatique));
		trainingRepository.save(new Training(null, "NodeJS", "Formation NodeJS", 175, 1,40, "NodeJS.jpg",true, informatique));


		trainingRepository.save(new Training(null, "Bases", "Les bases de la cuisine", 90, 1,0, "cuisineBases.jpg",true, cuisine));
		trainingRepository.save(new Training(null, "Cuisine du monde", "Les cuisines du monde", 300, 1,40, "world-cook.jpg",true, cuisine));
		trainingRepository.save(new Training(null, "Pâtisserie", "Les bases de la pâtisserie", 310, 1,40, "cookPatisserie.jpg",true, cuisine));
		trainingRepository.save(new Training(null, "Vegan", "Apprendre la cuisine vegan", 45, 1,40, "cookVegan.jpg",true, cuisine));
		trainingRepository.save(new Training(null, "Santé", " Élaboration de plats équilibrés", 155, 1,40, "default.jpg",true, cuisine));

		trainingRepository.save(new Training(null, "Anglais", "Formation à l'anglais", 150, 1,40, "british-flag.jpg",true, anglais));
		trainingRepository.save(new Training(null, "Espagnol", "Formation à l'espagnol", 100, 1,40, "spain-flag.jpg",true, anglais));
		trainingRepository.save(new Training(null, "Français", "Formation au français", 120, 1,40, "french-flag.jpg",true, anglais));
		trainingRepository.save(new Training(null, "Allemand", "Formation à l'allemand", 300, 1,40, "german-flag.jpg",true, anglais));
		trainingRepository.save(new Training(null, "Mandarin", "Formation au mandarin", 175, 1,40, "china-flag.jpg",true, anglais));

		trainingRepository.save(new Training(null, "Comptabilité", "Les bases de la comptabilité", 150, 1,40, "compta-bases.jpg",true, finance));
		trainingRepository.save(new Training(null, "Analyse", "Formation aux analyses financières", 100, 1,40, "fin-analyst.jpg",true, finance));
		trainingRepository.save(new Training(null, "Gestion", "Formation au gestion budgétaire", 120, 1,40, "fin-gestion.jpg", true,finance));
		trainingRepository.save(new Training(null, "Fiscalité", "Fiscalité des entreprises", 300, 1,40, "fin-impots.jpg",true, finance));
		trainingRepository.save(new Training(null, "Investissement", "Investissement et gestion de portefeuille", 175, 1,40, "default.jpg",true, finance));

		Customer customer = new Customer(null, "Lara", "Croft", "12 rue du manoir", "0403158698", "laracroft@gmail.com", ordersList);
		customerRepository.save(customer);
		Order order = new Order(null, 200.0, customer, "En cours");
		orderRepository.save(order);

		Customer customer2 = new Customer(null, "Lary", "Craft", "12 rue du manoir", "0403158698", "laracroft@gmail.com", ordersList);
		customerRepository.save(customer2);
		Order order2 = new Order(null, 200.0, customer2, "En cours");
		Order order3 = new Order(null, 350.0, customer2, "En cours");
		Order order4 = new Order(null, 1000.0, customer2, "En cours");
		Order order5 = new Order(null, 555.0, customer2, "En cours");
		Order order6 = new Order(null, 675.0, customer2, "En cours");
		Order order7 = new Order(null, 110.0, customer2, "En cours");

		orderRepository.save(order2);
		orderRepository.save(order3);
		orderRepository.save(order4);
		orderRepository.save(order5);
		orderRepository.save(order6);
		orderRepository.save(order7);

		Training training1 = new Training(null, "sport", "Formation Sport", 150, 1, 40, "défaut.jpg", true, anglais);
		trainingRepository.save(training1);
		Training training2 = new Training(null, "cuisine", "Formation Cuisine", 200, 1, 40, "défaut.jpg", true, cuisine);
		trainingRepository.save(training2);
		Training training3 = new Training(null, "tennis", "Formation Sport", 500, 1, 40, "défaut.jpg", true, anglais);
		trainingRepository.save(training3);

		orderItemRepository.save(new OrderItem(null, 5, 200, order2, training1));
		orderItemRepository.save(new OrderItem(null, 5, 200, order2, training2));
		orderItemRepository.save(new OrderItem(null, 5, 200, order2, training3));
	}

	private void generateUsersRoles(){
		accountService.saveUser(new AppUser(null,"gilles", "1234", new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"anonymous", "1234", new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"alejandra", "1234", new ArrayList<>()));

		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"USER"));
		accountService.saveRole(new AppRole(null,"ORDER_MANAGER"));

		accountService.addRoleToUser("gilles", "ADMIN");
		accountService.addRoleToUser("gilles", "USER");
		accountService.addRoleToUser("anonymous", "USER");
		accountService.addRoleToUser("alejandra", "ORDER_MANAGER");
		accountService.addRoleToUser("alejandra", "USER");

	}

}
