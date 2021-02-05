import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simple {
	public static void main(String[] args) throws Exception {
		System.out.println("Enter a number to continue..");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();

		
		Runnable ramrunnable =
				()->{
			
			for (int i=0; i<10000; i++) {
				List<String> list = new ArrayList<>();
				for (int j=0; j<10000; j++) {
					list.add("str"+j);					
				}
				
				System.out.println("Done with inner loop: "+i);
//				if (Thread.currentThread().getName().equals("pool-1-thread-3"))
				
				throw new  RuntimeException("Problem....");
			}
					
			System.out.println("Done with outer loop");
		};
		Runnable cpurunnable = ()->{
			
			for (int i=0; i<10000; i++) {
			
				for (int j=0; j<10000; j++) {
					int k = i*j;					
				}
				System.out.println("Done with inner loop: "+i);
			}
			System.out.println("Done with outer loop");
		};
	
	Thread t1 = new Thread(ramrunnable);
	t1.start();
		
/*	ExecutorService service = Executors.newCachedThreadPool();
	for(int i = 0;i<5;i++){
		//service.submit(ramrunnable);
		service.submit(cpurunnable);
	}
	Thread.sleep(10000);
	//service.submit(ramrunnable);
	}
	*/
	
	System.out.println("Enter a number to continue..");
	scanner.nextInt();

	
	}
}
