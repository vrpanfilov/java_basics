package practice.hospital;

public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {
        float temperatureMin = 32;
        float temperatureMax = 40;

        float[] temperatures = new float[patientsCount];
        for (int i = 0; i < patientsCount; i++) {
            temperatures[i] =
                    (float) (temperatureMin + (temperatureMax - temperatureMin) * Math.random());
        }
        return temperatures;
    }

    public static String getReport(float[] temperatureData) {
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
            Округлите среднюю температуру с помощью Math.round до 2 знаков после запятой,
            а температуры каждого пациента до 1 знака после запятой
        */

        String temperatures = "";
        float averageTemperature = 0;
        int healthyNumber = 0;
        float eps = 0.0001F;

        for (float temperature : temperatureData) {
            temperatures = temperatures.concat(String.format(" %2.1f", temperature));
            averageTemperature += temperature;
            if (temperature >= 36.2 - eps && temperature <= 36.9 + eps) {
                healthyNumber++;
            }
        }
        temperatures = temperatures.replaceAll(",", ".");
        averageTemperature /= temperatureData.length;

        String report =
                "Температуры пациентов:" + temperatures +
                        "\nСредняя температура: " +
                        String.format("%5.2f", averageTemperature).replaceAll(",", ".") +
                        "\nКоличество здоровых: " + healthyNumber;
        return report;
    }
}
