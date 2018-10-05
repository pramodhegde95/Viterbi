Viterbi Algorithm:

Implement the Viterbi Algorithm and run it with the HMM to compute the
most likely physical condition of a patient.


        normal cold dizzy
Healthy 0.1    0.4   0.5
Fever   0.6    0.3   0.1


        Healthy Fever
Healthy 0.7     0.3
Fever   0.5     0.5


        Healthy Fever
Start   0.6     0.4



input:
java Viterbi “DDNNCCDND”
(D-dizzy, N-normal, C-cold)

output:
HHFFHHHFH
(H-Healthy, F-Fever)
