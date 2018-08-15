public class Multithreading {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        Multithreading m = new Multithreading();
        m.someCalculationsOneThread();
        m.someCalculationsTwoThread();
    }

    public void someCalculationsOneThread(){
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1f;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println((float)(System.currentTimeMillis() - a)/1000+" sec");
        //System.out.println(arr[0]+" "+arr[h-1]+" "+arr[h]+" "+arr[size-1]);
    }

    public void someCalculationsTwoThread(){
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1f;
        }
        long a = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0,j=h; i < h; i++,j++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                }
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println((float)(System.currentTimeMillis() - a)/1000+" sec");
        //System.out.println(arr[0]+" "+arr[h-1]+" "+arr[h]+" "+arr[size-1]);
    }
}
