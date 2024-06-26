# StatisticsServise

## Описание
Эта программа предназначена для мониторинга выполнения методов, аннотированных специальным маркером @Metrics, в Java-приложениях. Она использует динамические прокси для перехвата вызовов методов и измерения времени их выполнения. Программа также предоставляет возможность генерации отчетов о метриках, включая сортировку и вывод данных.

## Основные компоненты
@Metrics: пользовательская аннотация, используемая для маркировки методов, которые необходимо отслеживать.
MetricsInvocationHandler: реализация InvocationHandler, которая перехватывает вызовы к аннотированным методам, измеряет время их выполнения и логирует результаты.
MyBeanProxy: реализация BeanPostProcessor, которая автоматически обертывает бины, содержащие аннотированные методы, в прокси для перехвата вызовов.
ReportMyMetrics: класс, отвечающий за создание отчетов о метриках, включая чтение данных из файла, преобразование в структуру данных и вывод отсортированных результатов.
Как использовать
Аннотация методов: Добавьте аннотацию @Metrics к методам, которые вы хотите отслеживать.
Настройка Spring: Убедитесь, что MyBeanProxy зарегистрирован в вашем Spring-приложении как BeanPostProcessor.
Запуск приложения: При запуске приложения методы, аннотированные @Metrics, будут автоматически обернуты в прокси, и их выполнение будет отслеживаться.
Генерация отчетов: Используйте ReportMyMetrics для создания отчетов о метриках, основанных на данных, собранных во время выполнения.
## Пример использования
@Service
public class MyService {
    @Metrics
    public void doSomething() {
        // Логика метода
    }
}
После запуска приложения, вызов doSomething() будет перехвачен и измерен. Результаты будут доступны в отчете, созданном с помощью ReportMyMetrics.
