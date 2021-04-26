package al3xandria.model.objects;

import java.util.ArrayList;

/**
 * Classe que crea les dades dels llibres 
 * y tres arrayList, autors, gerenes i editorials
 * Servirà per entregar el TEA3
 * @author SergioHernandez
 *
 */
public class CreateLlibres {
	private ArrayList<Llibres> llistatDeLlibres;
	private ArrayList<String> generes = new ArrayList<String>();
	private ArrayList<String> autors= new ArrayList<String>();
	private ArrayList<String> editorials= new ArrayList<String>();

	public CreateLlibres() {
		createLlibres();
	}
	
	private void createLlibres() {
		llistatDeLlibres = new ArrayList<Llibres>();
		Llibres llibre1 = new Llibres(1, "4567891234564", "Java a fondo", "11-05-2020", "Primera", 100, 2, sinopsi, 342, "Maximo Nuñez", "Rama", "Java", false);
		Llibres llibre2 = new Llibres(2, "4567891234561", "C a fondo", "21-06-1999", "Segona", 100, 0, sinopsi, 485, "Jose Bautista", "Anaya Multimedia", "C", false);
		Llibres llibre3 = new Llibres(3, "4567891234567", "C# a fondo", "11-11-1998", "Primera", 200, 3, sinopsi, 150, "Richard Deal", "Marcombo", "C", false);
		Llibres llibre4 = new Llibres(4, "4851234567894", "C++ a fondo", "02-12-2015", "Tercera", 874, 1, sinopsi, 547, "Arnesto Arigani", "Mc Graw C", "Java", true);
		Llibres llibre5 = new Llibres(5, "9876543216549", "Html a fondo", "06-09-2015", "Primera", 564, 2, sinopsi, 1100, "Maximo Nuñez", "Rama", "Web", false);
		Llibres llibre6 = new Llibres(6, "1594871263265", "Javascript a fondo", "23-05-2016", "Segona", 35, 1, sinopsi, 687, "Maximo Nuñez", "Anaya Multimedia", "Web", false);
		Llibres llibre7 = new Llibres(7, "1234568564895", "Rust a fondo", "15-01-2000", "Primera", 2, 2, sinopsi, 547, "Richard Deal", "Anaya Multimedia", "Android", true);
		Llibres llibre8 = new Llibres(8, "5689874582135", "CSS a fondo", "16-02-2021", "Primera", 78, 3, sinopsi, 569, "Richard Deal", "Rama", "Web", false);
		Llibres llibre9 = new Llibres(9, "4586543265984", "Visual Basic a fondo", "19-04-2001", "Tercera", 458, 5, sinopsi, 1254, "Richard Deal", "Mc Graw Gill", "Visual", true);
		Llibres llibre10 = new Llibres(10, "4587953321546", "Python a fondo", "30-09-1999", "Primera", 87, 1, sinopsi, 1001, "Richard Deal", "Rama", "Python", false);
		Llibres llibre11 = new Llibres(11, "5688844456315", "SQL a fondo", "28-10-2006", "Primera", 10, 1, sinopsi, 741, "Maximo Nuñez", "Marcombo", "SQL", true);
		Llibres llibre12 = new Llibres(12, "5566688877756", "Mysql a fondo", "01-02-2020", "Primera", 0, 2, sinopsi, 754, "Richard Deal", "Rama", "SQL", false);
		Llibres llibre13 = new Llibres(13, "1235556668574", "PHP a fondo", "11-05-2020", "Primera", 56, 2, sinopsi, 802, "Richard Deal", "Mc Graw Gill", "Web", false);
		
		llistatDeLlibres.add(llibre1);
		llistatDeLlibres.add(llibre2);
		llistatDeLlibres.add(llibre3);
		llistatDeLlibres.add(llibre4);
		llistatDeLlibres.add(llibre5);
		llistatDeLlibres.add(llibre6);
		llistatDeLlibres.add(llibre7);
		llistatDeLlibres.add(llibre8);
		llistatDeLlibres.add(llibre9);
		llistatDeLlibres.add(llibre10);
		llistatDeLlibres.add(llibre11);
		llistatDeLlibres.add(llibre12);
		llistatDeLlibres.add(llibre13);
		
		autors.add("Maximo Nuñez");
		autors.add("Jose Bautista");
		autors.add("Richard Deal");
		autors.add("Arnesto Arigani");
		
		generes.add("Java");
		generes.add("C");
		generes.add("Web");
		generes.add("Android");
		generes.add("Visual");
		generes.add("Python");
		generes.add("SQL");

		editorials.add("Rama");
		editorials.add("Anaya Multimedia");
		editorials.add("Marcombo");
		editorials.add("Mc Graw C");

	}
	
	public ArrayList<Llibres> getLlistatDeLlibres() {
		return llistatDeLlibres;
	}
	
	public ArrayList<String> getAutors() {
		return autors;
	}
	
	public ArrayList<String> getEditorials() {
		return editorials;
	}
	
	public ArrayList<String> getGeneres() {
		return generes;
	}
	
	private String sinopsi = "Fusce cursus ullamcorper tortor, ac sollicitudin orci accumsan sit amet. Nulla eget ";
			
			
}
