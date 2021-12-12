name: Noa Azoulay


compile: bin
	find src -name "*.java" > sources.txt
	javac -cp  biuoop-1.4.jar:. -d bin @sources.txt

run:
	java -cp biuoop-1.4.jar:resources:bin game/SpaceInvaders

bin:
	mkdir bin

jar:
	jar cfm space-invaders.jar Manifest.mf -C bin/ . -C resources/ .
