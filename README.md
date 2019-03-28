# LTL-Verifier

## Usage

Download distribution from releases -- https://github.com/EvgeniyaKatunina/LTL-Verifier/releases/tag/1.0 .
```
Usage: .\LTLVerifier <path to XML file with automaton> <LTL expression>
```
## Output Example
```
PS C:\Projects\LTLVerifier\build\distributions\LTLVerifier-1.0-SNAPSHOT\LTLVerifier-1.0-SNAPSHOT\bin> .\LTLVerifier.bat
.\small.xml "G (JUMP -> (F D))"
Correct
PS C:\Projects\LTLVerifier\build\distributions\LTLVerifier-1.0-SNAPSHOT\LTLVerifier-1.0-SNAPSHOT\bin> .\LTLVerifier.bat
.\small.xml "G (A -> (F B))"
Incorrect, counter example:
0: [A]
1: [A, GO]
2: [B]
3: [B, GO]
4: [A]
5: [A, JUMP]
6: [C]
7: [C, GO]
8: [D]
9: [D, GO]
Cycle start from index: 6
