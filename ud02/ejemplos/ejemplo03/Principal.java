public class Principal {
    
	public static void main(String[] args) {

		//información del hilo principal
        Thread.currentThread().setName("Principal");
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().toString());

		//creamos un grupo de hilos y un hilo base para crear los demás
        ThreadGroup grupo = new ThreadGroup("Mi grupo de hilos");
        HiloEjemplo2Grupos h = new HiloEjemplo2Grupos();

		Thread h1 = new Thread(grupo, h, "Hilo 1");
		Thread h2 = new Thread(grupo, h, "Hilo 2");
		Thread h3 = new Thread(grupo, h, "Hilo 3");		

		h1.start();
		h2.start();
		h3.start();

		System.out.println("Nombre del grupo de hilos " + grupo.getName());

		System.out.println("3 HILOS CREADOS...");
		System.out.println("Hilos activos: " + Thread.activeCount());
	}
    
}
