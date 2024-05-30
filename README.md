This repo contains a set of CTF challenges meant to take students from an introductory point to more advanced levels in mobile CTFing.

## Easy Challenges
### Logcat

This challenge is meant to introduce students to `adb` and the `logcat` tool, as well as decompilation, since it can be solved through reverse engineering as well. Although easy, it provides a good first step into mobile CTFing.

### Shaorma

This challenge is meant to introduce students to `jadx` for decompilation, to hash "decryption" and file obfuscation. This is a two step challange where the first step offers a hint for decrypting the flag.

### RedFlag

This challenge is very easy, look for strings and hashes to put together the pieces of the flag.


## Medium Challenges

### IVDrip

This challenge can be approached either by employing Frida to modify a segment of the flag decryption code, or by replicating the behavior of the decryption function in a different programming language, such as Python.

### Extraction

This challenge contains 3 flags - one is hidden within the drawable images via exiftool, another is a comment in a `.xml` file (with a hint for the drawable image), and the last one requires the students to decode a password in order to display a broken QR code. Once repaired, the QR code will lead to a flag hidden within junk value. The QR code can also be found without decoding the password.


## Hard Challenges

### Hermes
This task involves a simple TCP client-server chat application between two parties. Traffic is encrypted using AES, with the key being derived from a secret hardcoded in the APK file. A network capture is given that contains the communication between two parties. The flag will be revealed if all messages are succesfully decrypted.

The files which should be distributed to solvers are located in `dist`. For a solution, use `sol/run.sh` coupled with the associated java and python code. The source code of the application is located in `hermes`.
