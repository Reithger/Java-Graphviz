import java.io.File;

public class TestGraphViz {

	public static void main(String[] args) {
		String dot = "digraph G {\r\n" + 
				"	node[shape=record,style=filled,fillcolor=gray95];\r\n" + 
				"	edge[concentrate=true];\r\n" + 
				"	graph[splines = ortho, ranksep = 1, ratio = fill, color=blue];\r\n" + 
				"	rankdir = TB;\r\n" + 
				"\r\n" + 
				"	n0 [label = <{Function||}>];\r\n" + 
				"	n1 [label = <{JavaFile||}>];\r\n" + 
				"	n2 [label = <{ConvertVisual||}>];\r\n" + 
				"	n3 [label = <{PopoutPackageNavigator||}>];\r\n" + 
				"	n4 [label = <{UMLConfigValidation||}>];\r\n" + 
				"	n5 [label = <{InstanceVariable||}>];\r\n" + 
				"	n6 [label = <{Cluster||}>];\r\n" + 
				"	n7 [label = <{DotProcess||}>];\r\n" + 
				"	n8 [label = <{GenericInterface||}>];\r\n" + 
				"	n9 [label = <{Argument||}>];\r\n" + 
				"	n10 [label = <{DotComponent||}>];\r\n" + 
				"	n11 [label = <{<i>GenericFile</i>||}>];\r\n" + 
				"	n12 [label = <{Display||}>];\r\n" + 
				"	n13 [label = <{Constructor||}>];\r\n" + 
				"	n14 [label = <{GraphViz||}>];\r\n" + 
				"	n15 [label = <{GenericEnum||}>];\r\n" + 
				"	n16 [label = <{GenericClass||}>];\r\n" + 
				"	n17 [label = <{<i>ClassComponent</i>||}>];\r\n" + 
				"	n18 [label = <{FileFactory||}>];\r\n" + 
				"	n19 [label = <{Main||}>];\r\n" + 
				"	n20 [label = <{Node||}>];\r\n" + 
				"	n21 [label = <{<i>GenericDefinition</i>||}>];\r\n" + 
				"	n22 [label = <{Explore||}>];\r\n" + 
				"	subgraph cluster_{\r\n" + 
				"		label = \"\";\r\n" + 
				"		fontsize = 30;\r\n" + 
				"		penwidth = 1;\r\n" + 
				"		subgraph cluster_analysis{\r\n" + 
				"			label = \"analysis\";\r\n" + 
				"			fontsize = 26;\r\n" + 
				"			penwidth = 2;\r\n" + 
				"			subgraph cluster_analysis_language{\r\n" + 
				"				label = \"analysis.language\";\r\n" + 
				"				fontsize = 22;\r\n" + 
				"				penwidth = 3;\r\n" + 
				"				subgraph cluster_analysis_language_actor{\r\n" + 
				"					label = \"analysis.language.actor\";\r\n" + 
				"					fontsize = 18;\r\n" + 
				"					penwidth = 4;\r\n" + 
				"					n15;\r\n" + 
				"					n16;\r\n" + 
				"					n8;\r\n" + 
				"					n21;\r\n" + 
				"				}\r\n" + 
				"				subgraph cluster_analysis_language_component{\r\n" + 
				"					label = \"analysis.language.component\";\r\n" + 
				"					fontsize = 18;\r\n" + 
				"					penwidth = 4;\r\n" + 
				"					n0;\r\n" + 
				"					n13;\r\n" + 
				"					n17;\r\n" + 
				"					n5;\r\n" + 
				"					n9;\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"			subgraph cluster_analysis_process{\r\n" + 
				"				label = \"analysis.process\";\r\n" + 
				"				fontsize = 22;\r\n" + 
				"				penwidth = 3;\r\n" + 
				"				n6;\r\n" + 
				"				n22;\r\n" + 
				"				subgraph cluster_analysis_process_file{\r\n" + 
				"					label = \"analysis.process.file\";\r\n" + 
				"					fontsize = 18;\r\n" + 
				"					penwidth = 4;\r\n" + 
				"					n11;\r\n" + 
				"					n1;\r\n" + 
				"					n18;\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		subgraph cluster_image{\r\n" + 
				"			label = \"image\";\r\n" + 
				"			fontsize = 26;\r\n" + 
				"			penwidth = 2;\r\n" + 
				"			n2;\r\n" + 
				"			n14;\r\n" + 
				"			n7;\r\n" + 
				"			n10;\r\n" + 
				"		}\r\n" + 
				"		subgraph cluster_main{\r\n" + 
				"			label = \"main\";\r\n" + 
				"			fontsize = 26;\r\n" + 
				"			penwidth = 2;\r\n" + 
				"			n19;\r\n" + 
				"		}\r\n" + 
				"		subgraph cluster_ui{\r\n" + 
				"			label = \"ui\";\r\n" + 
				"			fontsize = 26;\r\n" + 
				"			penwidth = 2;\r\n" + 
				"			n12;\r\n" + 
				"			n3;\r\n" + 
				"			n4;\r\n" + 
				"			n20;\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"	n0 -> n17[arrowhead=onormal];\r\n" + 
				"	n0 -> n9[arrowhead=normal];\r\n" + 
				"	n1 -> n11[arrowhead=onormal];\r\n" + 
				"	n1 -> n1[arrowhead=none];\r\n" + 
				"	n2 -> n22[arrowhead=normal];\r\n" + 
				"	n2 -> n7[arrowhead=normal];\r\n" + 
				"	n2 -> n14[arrowhead=normal];\r\n" + 
				"	n3 -> n20[arrowhead=normal];\r\n" + 
				"	n4 -> n14[arrowhead=normal];\r\n" + 
				"	n4 -> n12[arrowhead=none];\r\n" + 
				"	n5 -> n17[arrowhead=onormal];\r\n" + 
				"	n6 -> n6[arrowhead=none];\r\n" + 
				"	n7 -> n16[arrowhead=normal];\r\n" + 
				"	n7 -> n21[arrowhead=normal];\r\n" + 
				"	n7 -> n6[arrowhead=normal];\r\n" + 
				"	n7 -> n22[arrowhead=normal];\r\n" + 
				"	n7 -> n10[arrowhead=normal];\r\n" + 
				"	n8 -> n21[arrowhead=onormal];\r\n" + 
				"	n11 -> n16[arrowhead=normal];\r\n" + 
				"	n11 -> n21[arrowhead=normal];\r\n" + 
				"	n11 -> n15[arrowhead=normal];\r\n" + 
				"	n11 -> n8[arrowhead=normal];\r\n" + 
				"	n11 -> n6[arrowhead=normal];\r\n" + 
				"	n11 -> n11[arrowhead=none];\r\n" + 
				"	n12 -> n2[arrowhead=normal];\r\n" + 
				"	n12 -> n3[arrowhead=normal];\r\n" + 
				"	n13 -> n0[arrowhead=onormal];\r\n" + 
				"	n13 -> n9[arrowhead=normal];\r\n" + 
				"	n15 -> n21[arrowhead=onormal];\r\n" + 
				"	n16 -> n21[arrowhead=onormal];\r\n" + 
				"	n16 -> n5[arrowhead=normal];\r\n" + 
				"	n18 -> n11[arrowhead=normal];\r\n" + 
				"	n18 -> n1[arrowhead=normal];\r\n" + 
				"	n19 -> n2[arrowhead=normal];\r\n" + 
				"	n19 -> n12[arrowhead=normal];\r\n" + 
				"	n20 -> n20[arrowhead=none];\r\n" + 
				"	n21 -> n13[arrowhead=normal];\r\n" + 
				"	n21 -> n0[arrowhead=normal];\r\n" + 
				"	n21 -> n21[arrowhead=none];\r\n" + 
				"	n22 -> n21[arrowhead=normal];\r\n" + 
				"	n22 -> n18[arrowhead=normal];\r\n" + 
				"	n22 -> n11[arrowhead=normal];\r\n" + 
				"	n22 -> n6[arrowhead=normal];\r\n" + 
				"\r\n" + 
				"}";
		File f = new File("./test");
		f.mkdir();
		try {
			GraphViz.generateDotImage("./test", dot, "test", 4);
			//also write the correct version of that to be safe
			System.out.println("Test image written to \"<local directory>/test/test.jpg\", please verify the correctness of the generated image against the companion image \"<local directory>\"/test/correct.png");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong! Whoops. Could be an OS issue, contact the writer of this library to try and fix it.");
		}
	}
	
}
