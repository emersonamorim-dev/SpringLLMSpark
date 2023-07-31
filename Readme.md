## Projeto para SpringLLMSpark 
Codificação em Java com uso do Framework SpringBoot para desenvolvimento de é uma aplicação que cria uma LLM - Large Language Model para elaboração de Modelo de Linguagem Larga para implementação de uma Inteligência Artificial que utiliza Apache Kafka para trabalhar com grande quantidade de dados através de Streaming de Dados e Apache Spark para fazer a parte de lapidação de grande quantidade de dados e serão enviados para o MongoDB para ingestão, processamento e armazenamento de dados.

**Pré-requisitos**
Java 8 ou superior
Maven
Kafka
Spark
MongoDB

**Como usar**
Inicie o seu servidor Kafka, Spark e MongoDB.
Clone este repositório e navegue até o diretório do projeto.
Execute o comando mvn spring-boot:run para iniciar a aplicação.
A aplicação agora está pronta para receber solicitações HTTP.

**Endpoints**
**POST /ingest**

Este endpoint aceita uma string no corpo da solicitação e a envia para um tópico Kafka.

**POST /processing/start**

Este endpoint inicia o processamento dos dados recebidos do Kafka.

**GET /processing/status**

Este endpoint retorna o status do processamento dos dados.

**POST /storage/store**

Este endpoint armazena os dados processados no MongoDB.

**GET /storage/retrieve**

Este endpoint recupera os dados armazenados do MongoDB.

**Serviços**

**DataIngestionService**

Este serviço é responsável pela ingestão de dados. Ele recebe dados e os envia para um tópico Kafka. Os dados também são salvos no MongoDB.

**DataProcessingService**

Este serviço é responsável pelo processamento de dados. Ele recebe dados do Kafka, processa-os usando o Apache Spark e salva os dados processados no MongoDB.

**DataStorageService**

Este serviço é responsável pelo armazenamento de dados. Ele armazena e recupera dados do MongoDB.

**SparkService**

Este serviço é responsável pela leitura e processamento de dados usando o Apache Spark.

**KafkaConsumerService.java**

Este é um serviço que consome mensagens de um tópico Kafka. Ele usa a anotação @KafkaListener para definir um ouvinte Kafka que escuta mensagens de um tópico específico.

Quando uma mensagem é recebida, o método listen(String message) é chamado. Este método lê a mensagem como um Dataset<Row> usando o Apache Spark e imprime o conteúdo do Dataset.

O método sendMessage(String string, String data) parece estar vazio e não está sendo usado.

**KafkaProducerService.java**

Este é um serviço que produz mensagens para um tópico Kafka. Ele usa a classe KafkaTemplate do Spring Kafka para enviar mensagens.

O método sendMessage(String topic, String message) é usado para enviar uma mensagem para um tópico Kafka específico. Ele usa o método send do KafkaTemplate para enviar a mensagem.

Por favor, note que você precisa configurar o KafkaTemplate e o KafkaListener com as propriedades corretas do Kafka (como o endereço do servidor Kafka) para que eles possam se conectar ao Kafka corretamente. Isso geralmente é feito no arquivo application.properties ou application.yml da sua aplicação Spring Boot.

**Configuração**

A configuração do Kafka, Spark e MongoDB é definida no arquivo application.properties. Você pode modificar estas propriedades para se adequar ao seu ambiente.

Autor:
**Emerson Amorim**
