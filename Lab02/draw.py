import matplotlib.pyplot as plt
import numpy as np
import sys

if __name__ == "__main__":
    fileNames = ["TwoSum.txt", "ThreeSum.txt", "TwoSumFast.txt", "ThreeSumFast.txt"]
    # fileNames = ["TwoSumFast-6.txt"]
    for name in fileNames:
        # test = "ThreeSum.txt"
        n = [1, 2, 4, 8, 16, 32] # Kints
        # n = [1, 2, 4, 8, 16, 32, 1000] # Kints, 1000Kints == 1Mints
        # read in the data
        with open(name, "r") as f:
            # Line example:  255181   629.2   20230911_211717  tbiswas2  16Kints.txt
            lines = f.readlines()
            T_n = [float(line.split()[1]) for line in lines]
            # init a new figure
            plt.figure()
            # add grid
            plt.grid()
            # plot n vs T_n
            plt.plot(n, T_n, 'o-')
            # Logarithmic scale for x-Axis
            # plt.yscale("log", base=2)
            plt.xscale("log", base=2)
            plt.xlabel("n")
            plt.ylabel("T(n)")
            plt.title(name)
            # save the plot
            plt.savefig(name[:-4] + ".png")
        # sys.exit(0)
        # Plot n vs T(n) for TwoSum.java and TwoSumFast.java on the same ﬁgure. Use logarithmic scale for x-Axis.
        s1 = fileNames[0]
        s2 = fileNames[2]
        with open(s1, "r") as f1, open(s2, "r") as f2:
            lines1 = f1.readlines()
            lines2 = f2.readlines()
            T_n1 = [float(line.split()[1]) for line in lines1]
            T_n2 = [float(line.split()[1]) for line in lines2]
            # init a new figure
            plt.figure()
            plt.grid()
            plt.plot(n, T_n1, 'o-', label="TwoSum.java")
            plt.plot(n, T_n2, 'o-', label="TwoSumFast.java")
            # Logarithmic scale for x-Axis
            plt.xscale("log", base=2)
            plt.xlabel("n")
            plt.ylabel("T(n)")
            plt.title("Comparison of TwoSum.java and TwoSumFast.java")
            plt.legend()
            # save the plot
            plt.savefig("TwoSum-Compare.png")

        # Plot n vs T(n) for ThreeSum.java and ThreeSumFast.java on the same ﬁgure. Use logarithmic scale for x-Axis.
        s1 = fileNames[1]
        s2 = fileNames[3]
        with open(s1, "r") as f1, open(s2, "r") as f2:
            lines1 = f1.readlines()
            lines2 = f2.readlines()
            T_n1 = [float(line.split()[1]) for line in lines1]
            T_n2 = [float(line.split()[1]) for line in lines2]
            # init a new figure
            plt.figure()
            plt.grid()
            plt.plot(n, T_n1, 'o-', label="ThreeSum.java")
            plt.plot(n, T_n2, 'o-', label="ThreeSumFast.java")
            # Logarithmic scale for x-Axis
            plt.xscale("log", base=2)
            plt.xlabel("n")
            plt.ylabel("T(n)")
            plt.title("Comparison of ThreeSum.java and ThreeSumFast.java")
            plt.legend()
            # save the plot
            plt.savefig("ThreeSum-Compare.png")

    