package br.com.fiap.on_data_thymeleaf.config;

import br.com.fiap.on_data_thymeleaf.entity.*;
import br.com.fiap.on_data_thymeleaf.entity.Categoria;
import br.com.fiap.on_data_thymeleaf.entity.Genero;
import br.com.fiap.on_data_thymeleaf.entity.Gravidade;
import br.com.fiap.on_data_thymeleaf.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private DoencaRepository doencaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Override
    @Transactional
    public void run(String... args) {
        // Initialize Users (Keep original functionality)
        initializeUsers();

        // Initialize Dental Clinic Data
        initializeDentalClinicData();
    }

    private void initializeUsers() {
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEnabled(true);
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEnabled(true);
            user.setRole(Role.USER);

            userRepository.save(user);
        }
    }

    private void initializeDentalClinicData() {
        // Clean existing data
        ocorrenciaRepository.deleteAll();
        pacienteRepository.deleteAll();
        doencaRepository.deleteAll();
        dentistaRepository.deleteAll();
        clinicaRepository.deleteAll();

        // Initialize Clinicas
        List<Clinica> clinicas = List.of(
                createClinica("Clinica Sorriso Feliz", "Rua das Flores, 123", "São Paulo", 150.00),
                createClinica("Clinica Dental Care", "Avenida Brasil, 456", "Rio de Janeiro", 200.00),
                createClinica("Clinica DentArt", "Rua das Palmeiras, 789", "Belo Horizonte", 180.00),
                createClinica("Clinica OdontoPlus", "Avenida Paulista, 1011", "São Paulo", 250.00),
                createClinica("Clinica Sorriso Perfeito", "Rua dos Pinheiros, 1213", "Curitiba", 220.00)
        );
        clinicaRepository.saveAll(clinicas);

        // Initialize Dentistas
        List<Dentista> dentistas = List.of(
                createDentista("Dr. João Silva", 35, "12345", 5000.00, clinicas.get(0)),
                createDentista("Dra. Maria Oliveira", 40, "67890", 5500.00, clinicas.get(1)),
                createDentista("Dr. Pedro Souza", 38, "54321", 5200.00, clinicas.get(2)),
                createDentista("Dra. Ana Costa", 42, "98765", 6000.00, clinicas.get(3)),
                createDentista("Dr. Carlos Pereira", 37, "11223", 5300.00, clinicas.get(4))
        );
        dentistaRepository.saveAll(dentistas);

        // Initialize Doencas with proper enum values
        List<Doenca> doencas = List.of(
                createDoenca("Cárie", 0.3, Gravidade.MEDIA),
                createDoenca("Gengivite", 0.2, Gravidade.BAIXA),
                createDoenca("Periodontite", 0.4, Gravidade.ALTA),
                createDoenca("Bruxismo", 0.1, Gravidade.MEDIA),
                createDoenca("Sensibilidade Dentária", 0.25, Gravidade.BAIXA)
        );
        doencaRepository.saveAll(doencas);

        // Initialize Pacientes with proper enum values
        List<Paciente> pacientes = List.of(
                createPaciente("Lucas Mendes", Genero.MASCULINO, 28, "lucas@mail.com", "Rua das Acácias, 321", "São Paulo", false, 3000.00, 2.5, Categoria.A),
                createPaciente("Fernanda Lima", Genero.FEMININO, 34, "fernanda@mail.com", "Avenida das Palmeiras, 654", "Rio de Janeiro", true, 4500.00, 3.0, Categoria.B),
                createPaciente("Ricardo Alves", Genero.MASCULINO, 45, "ricardo@mail.com", "Rua dos Ipês, 987", "Belo Horizonte", false, 6000.00, 1.5, Categoria.A),
                createPaciente("Patrícia Santos", Genero.FEMININO, 29, "patricia@mail.com", "Avenida das Flores, 1112", "São Paulo", true, 3500.00, 2.0, Categoria.C),
                createPaciente("Gabriel Costa", Genero.NAO_BINARIO, 50, "gabriel@mail.com", "Rua das Orquídeas, 1314", "Curitiba", false, 7000.00, 4.0, Categoria.B)
        );
        pacienteRepository.saveAll(pacientes);

        // Initialize Ocorrencias
        List<Ocorrencia> ocorrencias = List.of(
                createOcorrencia("2025-01-15 10:00:00", "OC001", 150.00, 2, true, pacientes.get(0), doencas.get(0), dentistas.get(0)),
                createOcorrencia("2025-02-20 11:30:00", "OC002", 200.00, 1, false, pacientes.get(1), doencas.get(1), dentistas.get(1)),
                createOcorrencia("2025-03-25 09:45:00", "OC003", 180.00, 3, true, pacientes.get(2), doencas.get(2), dentistas.get(2)),
                createOcorrencia("2025-04-10 14:15:00", "OC004", 250.00, 2, true, pacientes.get(3), doencas.get(3), dentistas.get(3)),
                createOcorrencia("2025-05-05 08:00:00", "OC005", 220.00, 1, false, pacientes.get(4), doencas.get(4), dentistas.get(4)),
                createOcorrencia("2025-06-12 13:30:00", "OC006", 300.00, 4, true, pacientes.get(0), doencas.get(1), dentistas.get(2)),
                createOcorrencia("2025-07-18 16:45:00", "OC007", 350.00, 2, true, pacientes.get(1), doencas.get(2), dentistas.get(3)),
                createOcorrencia("2025-08-22 12:00:00", "OC008", 400.00, 3, false, pacientes.get(2), doencas.get(3), dentistas.get(4)),
                createOcorrencia("2025-09-30 10:30:00", "OC009", 450.00, 1, true, pacientes.get(3), doencas.get(4), dentistas.get(0)),
                createOcorrencia("2025-10-05 15:00:00", "OC010", 500.00, 2, true, pacientes.get(4), doencas.get(0), dentistas.get(1)),
                createOcorrencia("2025-11-05 16:00:00", "OC011", 150.00, 4, false, pacientes.get(1), doencas.get(0), dentistas.get(3)),
                createOcorrencia("2025-12-05 18:00:00", "OC012", 100.00, 5, true, pacientes.get(3), doencas.get(2), dentistas.get(4))
        );
        ocorrenciaRepository.saveAll(ocorrencias);

        System.out.println("Database has been initialized with sample dental clinic data.");
    }

    private Clinica createClinica(String nome, String endereco, String cidade, double custoMedioConsulta) {
        Clinica clinica = new Clinica();
        clinica.setNome(nome);
        clinica.setEndereco(endereco);
        clinica.setCidade(cidade);
        clinica.setCustoMedioConsulta(custoMedioConsulta);
        return clinica;
    }

    private Dentista createDentista(String nome, int idade, String registro, double salario, Clinica clinica) {
        Dentista dentista = new Dentista();
        dentista.setNome(nome);
        dentista.setIdade(idade);
        dentista.setRegistro(registro);
        dentista.setSalario(salario);
        dentista.setClinica(clinica);
        return dentista;
    }

    private Doenca createDoenca(String nome, double taxaReincidencia, Gravidade gravidade) {
        Doenca doenca = new Doenca();
        doenca.setNome(nome);
        doenca.setTaxaReincidencia(taxaReincidencia);
        doenca.setGravidade(gravidade);
        return doenca;
    }

    private Paciente createPaciente(String nome, Genero genero, int idade, String email,
                                    String endereco, String cidade, boolean fumante,
                                    double renda, double visitasPorAno, Categoria categoria) {
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setGenero(genero);
        paciente.setIdade(idade);
        paciente.setEmail(email);
        paciente.setEndereco(endereco);
        paciente.setCidade(cidade);
        paciente.setFumante(fumante);
        paciente.setRenda(renda);
        paciente.setVisitasPorAno(visitasPorAno);
        paciente.setCategoria(categoria);
        return paciente;
    }

    private Ocorrencia createOcorrencia(String dataString, String codigoOcorrencia, double valor,
                                        int duracaoHoras, boolean aprovado, Paciente paciente,
                                        Doenca doenca, Dentista dentista) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setData(LocalDateTime.parse(dataString, formatter));
        ocorrencia.setCodigoOcorrencia(codigoOcorrencia);
        ocorrencia.setValor(valor);
        ocorrencia.setDuracaoHoras(duracaoHoras);
        ocorrencia.setAprovado(aprovado);
        ocorrencia.setPaciente(paciente);
        ocorrencia.setDoenca(doenca);
        ocorrencia.setDentista(dentista);
        return ocorrencia;
    }
}
