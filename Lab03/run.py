import os
import csv
import pandas as pd
import matplotlib.pyplot as plt

TARGET_DIR = "."
# read in all the listed files
if __name__ == "__main__":
    # tables: a, b, c, d
    
    with open(os.path.join(TARGET_DIR, "result.txt"), "r") as f:
        # sample line: Arrays.sort() a 0.000000   20230922_232403  tbiswas2  1Kints.txt
        # method, table, time, timestamp, netid, filename
        # read in one line and split it
        lines = f.readlines()
    row_index = ["Arrays.sort()", "BubbleSort", "SelectionSort", "InsertionSort", "Mergesort", "Quicksort"]
    col_index = ["1Kints.txt", "2Kints.txt", "4Kints.txt", "8Kints.txt", "16Kints.txt", "32Kints.txt"]
    # construct 4 tables
    table_a = pd.DataFrame(index=row_index, columns=col_index)
    table_b = pd.DataFrame(index=row_index, columns=col_index)
    table_c = pd.DataFrame(index=row_index, columns=col_index)
    table_d = pd.DataFrame(index=row_index, columns=col_index)
    for line in lines:
        line = line.strip().split()
        # assign the vars
        method, table, time, timestamp, netid, filename = line
        if table == "a":
            table_a.loc[method, filename] = float(time)
        elif table == "b":
            table_b.loc[method, filename] = float(time)
        elif table == "c":
            table_c.loc[method, filename] = float(time)
        elif table == "d":
            table_d.loc[method, filename] = float(time)
        
    # save the tables
    table_a.to_csv(os.path.join(TARGET_DIR, "table_a.csv"))
    table_b.to_csv(os.path.join(TARGET_DIR, "table_b.csv"))
    table_c.to_csv(os.path.join(TARGET_DIR, "table_c.csv"))
    table_d.to_csv(os.path.join(TARGET_DIR, "table_d.csv"))
    # plot the tables
    # take array a as an example, print all the methods in one plot
    plt.figure()
    
    # use a subplot to represent the 4 arrays
    # make the plot larger
    for i in range(4):
        table = [table_a, table_b, table_c, table_d][i]
        name = ['a', 'b', 'c', 'd'][i]
        plt.figure()
        # print each row in the table as a line
        for row in table.index:
            plt.plot(table.loc[row], label=row)
        # set the legend
        plt.legend()
        # set the title
        plt.title(f"Array {name}")
        plt.grid()
        plt.xlabel("n")
        plt.ylabel("Time (s)")
        # Logarithmic scale for x-Axis depicting input size
        # plt.xscale("log", base=2)

        plt.savefig(os.path.join(TARGET_DIR, f"plot_{name}.png"))
