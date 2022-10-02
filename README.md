# Input-Space-Partition-Test-Generator
ISP starts with a finite (usually small) set of characteristics. For example, characteristics to describe MS students might be: major, year started, status, and visa. Each characteristic is divided into a finite (usually small) set of blocks. For example, the student characteristics may have the following blocks:

#### major = [swe, cs, infs, other]
#### year started = [2022, 2021, 2020 or earlier]
#### status = [part-time, full-time]
#### visa = [US, student, other]

Software testers often use one letter abstract names for characteristics and blocks. For example, A = major, B = year started, C = status, and D = visa. Then the blocks are simplified to A = [A1, A2, A3, A4]; B = [B1, B2, B3]; C = [C1, C2]; and D = [D1, D2, D3]. Software testers create test inputs for software by designing characteristics and blocks for the system interfaces, then combining blocks in one of several ways to create complete tests. With the MS student example, the blocks can be combined to form a maximum of 4*3*2*3 = 72 possible tests (more examples are below under ISP criteria).
