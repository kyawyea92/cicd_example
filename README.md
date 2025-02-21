# Spring Boot base on GitHub Action
Sure! Below is an example of a GitHub README file for your Spring Boot project with GitHub Actions, which includes the two jobs: `build` and `prod`.

```markdown
# Spring Boot Project with GitHub Actions

This repository contains a Spring Boot project with a CI/CD pipeline using GitHub Actions. The pipeline consists of two jobs:

- **Build Job**: Builds the Spring Boot application on the `dev` branch.
- **Prod Job**: Runs the production build and releases the version when changes are merged into the `prod` branch.

## Workflow Overview

- **Dev Branch**: Developers will work on this branch. Whenever there is a push or pull request to the `dev` branch, the **Build Job** will run to ensure the application builds correctly.
  
- **Prod Branch**: After code review and merging, changes from the `dev` branch are merged into the `prod` branch. This triggers the **Prod Job** to release the production version of the application.

## GitHub Actions Workflow

The GitHub Actions pipeline is defined in the `.github/workflows/ci-cd.yml` file and consists of two main jobs:

1. **Build Job**: This job runs on the `dev` branch to build the Spring Boot application.
2. **Prod Job**: This job runs on the `prod` branch to deploy the Spring Boot application to the production environment.

### Example GitHub Actions Workflow Configuration

```yaml
name: CI/CD Pipeline

on:
  push:
    branches:
      - dev
      - prod
  pull_request:
    branches:
      - dev

jobs:
  build:
    runs-on: ubuntu-latest
    if: github.ref == 'refs/heads/dev'  # Only run on dev branch
    steps:
      - name: Checkout Repository
        uses: actions/checkout@v2

      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'

      - name: Build with Maven
        run: mvn clean install -DskipTests

      - name: Run Tests
        run: mvn test

  prod:
    runs-on: ubuntu-latest
    if: github.ref == 'refs/heads/prod'  # Only run on prod branch
    needs: build  # Ensure that the build job completes first
    steps:
      - name: Checkout Repository
        uses: actions/checkout@v2

      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'

      - name: Build with Maven
        run: mvn clean install -DskipTests

      - name: Deploy to Production
        run: |
          echo "Deploying Spring Boot application to production"
          # Add deployment commands here (e.g., SSH, Docker, etc.)

```

### Workflow Details

1. **Build Job**:
   - **Trigger**: Runs on pushes and pull requests to the `dev` branch.
   - **Steps**:
     - Checks out the repository.
     - Sets up Java Development Kit (JDK 17).
     - Builds the application using Maven (`mvn clean install`).
     - Runs tests to ensure the integrity of the application.

2. **Prod Job**:
   - **Trigger**: Runs on pushes to the `prod` branch.
   - **Steps**:
     - Checks out the repository.
     - Sets up JDK 17.
     - Builds the application using Maven (`mvn clean install`).
     - Deploys the application to the production environment (you can customize the deployment command to suit your needs, such as Docker, SSH, etc.).

### How the Workflow Works

1. **Develop on `dev` branch**: Developers work on the `dev` branch. When pushing changes to `dev` or creating a pull request to it, the **Build Job** is triggered.
  
2. **Merge to `prod` branch**: Once changes in `dev` are reviewed and tested, they are merged into the `prod` branch. This triggers the **Prod Job**, which builds and deploys the application to the production environment.

### Requirements

- GitHub repository with Spring Boot project.
- A valid Maven build setup for your Spring Boot application.
- GitHub Actions enabled on your repository.
- A proper deployment environment for production (e.g., servers, cloud provider, etc.).

### How to Customize

- **JDK Version**: You can change the `java-version` under `actions/setup-java` to suit your application's Java version.
  
- **Deployment Steps**: In the **Prod Job**, you will need to replace the deployment placeholder with actual commands to deploy your application (e.g., SSH, Docker, Kubernetes, etc.).

## Contributing

Feel free to fork this project, make changes, and submit pull requests. Make sure to test your changes in the `dev` branch before merging to `prod`.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
