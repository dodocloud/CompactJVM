MI-RUN semestr�lka
Auto�i: Adam Veseck� (svecadam), Jan Havl��ek (havlij17) 


------------------------------------------
Implementace Java Virtual Machine v Jav�
------------------------------------------

***********************************
Projekty
***********************************
***********************************

CompactJVM
- virtu�ln� stroj

parametry:
- cesta do ko�enov�ho adres��e s aplikac�
- cesta do ko�enov�ho adres��e s knihovnami (zkompilovan� CompactJVMLib)
- namespace main t��dy (nap�. satSolver/Main)
- argumenty pro spou�t�n� program

-------------------

CompactJVMLab
- knihovna dummy objekt� a nativn�ch proxy metod
- mus� b�t includov�na projektem, kter� pob�� v CompactJVM

-------------------

SatSolver
- SAT �e�i�

parametry:
- vstupn� soubor s klauzulemi
- v�stupn� soubor pro z�pis


***********************************
Build:
***********************************
***********************************


V adres��i projekt� CompactJVM spustit tyto p��kazy:
ant compile
ant jar

V adres��i projektu SatSolver spustit tento p��kaz:
ant compile


Pro spu�t�n� projektu SatSolver nad testovac�mi daty pak sta�� v adres��i CompactJVM/build/jar spustit n�sleduj�c� p��kaz:
java -jar CompactJVM.jar ../../../SatSolver/build/classes/ ../../../CompactJVMLib/build/classes/ satSolver/Main ../../../SatSolver/cnf.txt result.txt

-- v metod� Main projektu CompactJVM je mo�no povyp�nat jednotliv� typy log�


***********************************
Vstupn� soubor:
***********************************
***********************************

Vstupn� soubor odpov�d� b�n� konvenci pro zad�n� SAT probl�mu.
 - ��dek za��naj�c� znakem "c" obsahuje koment��
 - n�sleduje ��dek za��naj�c� znakem "p" a ten d�le obsahuje 2 hodnoty odd�len� mezerou:
    - 1. po�et prom�nn�ch (M)
    - 2. po�et klauzul� (N)
 - d�le n�sleduje N ��dk� obsahuj�c� klauzule v podob� ��sel odd�len�ch mezerou. Ka�d� ��slo (1 a� M) p�edstavuje prom�nnou a pokud je ��slo z�porn�, je prom�nn� negov�na. Na konci ��dku je ukon�ovac� znak "0".
 
 P��klad:
 c Testovac� soubor cnf
 p 3 4
 1 -2 3
 -1 2 -3
 1 -3
 2 -3
    
    
***********************************
V�stupn� soubor:
***********************************
***********************************

Obsahuje zpr�vu, zda je zadan� v�raz splniteln� nebo ne, pokud splniteln� je, jsou vyps�ny hodnoty True nebo False v po�ad� ozna�en� prom�nn�ch, nap�:
[True True False] znamen� prom�nn� 1 = True, 2 = True, 3 = False.


***********************************
Features:
***********************************
***********************************

Garbage collector:
- pou�it Mark&Copy, vyhled�v� pou��van� objekty, kter� zkop�ruje do druh� poloviny heapy, ta prvn� je pot� zahozena

Exceptions:
- podpora v�jimek uvnit� i vn� b��c� aplikace (ArithmeticException, NullPointerException)

Nativn� metody
- z�pis do souboru a v�stup do konzole je �e�en pomoc� nativn�ch metod
- tyto metody jsou implementov�ny v CompactJVM

D�di�nost


***********************************



Seznam implementovan�ch instrukc�:
- aaload
- aastore
- aconstnull
- aload
- anewarray
- areturn
- astore
- athrow
- arraylength
- baload
- bipush
- caload
- castore
- dup
- fconst
- getstatic
- getfield
- goto
- iaload
- iastore
- iadd
- iconst
- idiv
- iinc
- iload
- iloadN
- imul
- ineg
- ireturn
- istore
- istoreN
- isub
- ificmpge
- ificmpgt
- ificmpeq
- ificmpne
- ifeq
- ifge
- ifgt
- iflt
- ifle
- ifne
- ifnonnull
- ifnull
- invokespecial
- invokestatic
- invokevirtual
- lconstn
- lload
- lloadn
- ldc
- newarray
- new
- nop
- pop
- putstatic
- putfield
- sipush

