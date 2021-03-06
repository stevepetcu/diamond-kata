DOCKER_MACHINE_NAME := dk.java.testng-akka-tdd-consumer
DOCKER_MACHINE_STATE := $(shell docker-machine ls --filter name=$(DOCKER_MACHINE_NAME) --format "{{.State}}")
EVALUATED_DOCKER_MACHINE_NAME := ${DOCKER_MACHINE_NAME}

.DEFAULT_GOAL := run
.PHONY: all confirm build-up test package run check-env

all: confirm build-up package run

confirm:
	@(read -p "Running this command may take a while. Are you sure? [y/N]: " sure && case "$$sure" in [yY]) true;; *) false;; esac)

build-up: check-env
	docker-compose up --build -d

test: check-env
	docker-compose exec maven mvn clean test

package: check-env
	docker-compose exec maven mvn clean package

run: check-env
	docker-compose exec java java -cp target/testng-akka-tdd-consumer-1.0-SNAPSHOT.jar codedojo.kata.testngakkatddconsumer.diamond.Main

check-env:
ifneq ($(DOCKER_MACHINE_STATE),Running)
	$(warning Docker machine "$(DOCKER_MACHINE_NAME)" in unexpected state: "$(DOCKER_MACHINE_STATE)" (expected: "Running").)
	ifeq ($(DOCKER_MACHINE_STATE),Stopped)
		$(warning Please run the following command to start and evaluate the machine:)
		$(warning docker-machine start $(DOCKER_MACHINE_NAME) && eval $$(docker-machine env $(DOCKER_MACHINE_NAME)))
	else
		$(warning Please run the following command to create and evaluate the machine:)
		$(warning docker-machine create $(DOCKER_MACHINE_NAME) && eval $$(docker-machine env $(DOCKER_MACHINE_NAME)))
	endif
	$(error Unable to confirm docker-machine $(DOCKER_MACHINE_NAME) running)
endif
ifneq ($(EVALUATED_DOCKER_MACHINE_NAME),$(DOCKER_MACHINE_NAME))
	$(warning Docker machine "$(DOCKER_MACHINE_NAME)" is not evaluated in the current terminal. Please run the following command:)
	$(warning eval $$(docker-machine env $(DOCKER_MACHINE_NAME)))
	$(error Couldn't connect to Docker daemon)
endif
