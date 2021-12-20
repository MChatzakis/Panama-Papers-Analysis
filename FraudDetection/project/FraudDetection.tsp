<?xml version="1.0" encoding="UTF-8"?><TSX>
  <project xmlMinorVersion="0" xmlVersion="48">
    <preferences>
      <preference name="colorTheme:webColorTheme" value="Silver"/>
      <preference name="behavior:progressDelay" value="1500"/>
    </preferences>
    <compositeUIs/>
    <domainModelGeneratorOptions/>
    <module tags="Node, Entity, Officer, Address, Intermediaries, Edges, Intermediary">
      <schema name="Network">
        <attributeTypes/>
        <modelElementTypes>
          <modelElementType name="Node" objectModelType="node">
            <attributeTypes>
              <attributeType identifier="true" name="node_id">
                <type name="IntegerValue"/>
              </attributeType>
              <attributeType identifier="false" name="name">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="country_code">
                <type name="StringValue"/>
              </attributeType>
            </attributeTypes>
          </modelElementType>
          <modelElementType name="Entity" objectModelType="node" parents="Node">
            <attributeTypes>
              <attributeType identifier="false" name="jurisdiction">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="incorporation_date">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="inactivation_date">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="struck_off_date">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="closed_date">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="ibcRUC">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="status">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="company">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="service_provider">
                <type name="StringValue"/>
              </attributeType>
            </attributeTypes>
          </modelElementType>
          <modelElementType name="Officer" objectModelType="node" parents="Node">
            <attributeTypes/>
          </modelElementType>
          <modelElementType name="Address" objectModelType="node" parents="Node">
            <attributeTypes>
              <attributeType identifier="false" name="address">
                <type name="StringValue"/>
              </attributeType>
            </attributeTypes>
          </modelElementType>
          <modelElementType name="Intermediary" objectModelType="node" parents="Node">
            <attributeTypes>
              <attributeType identifier="false" name="status">
                <type name="StringValue"/>
              </attributeType>
            </attributeTypes>
          </modelElementType>
          <modelElementType name="Edges" objectModelType="edge" sourceType="Node" targetType="Node">
            <attributeTypes>
              <attributeType identifier="true" name="start_id">
                <type name="ReferenceValue">
                  <elementTypeName value="Node"/>
                  <attributeTypeName value="node_id"/>
                  <bidirectionalMapping value="false"/>
                </type>
              </attributeType>
              <attributeType identifier="true" name="end_id">
                <type name="ReferenceValue">
                  <elementTypeName value="Node"/>
                  <attributeTypeName value="node_id"/>
                  <bidirectionalMapping value="false"/>
                </type>
              </attributeType>
              <attributeType identifier="false" name="type">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="link">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="start_date">
                <type name="StringValue"/>
              </attributeType>
              <attributeType identifier="false" name="end_date">
                <type name="StringValue"/>
              </attributeType>
            </attributeTypes>
          </modelElementType>
        </modelElementTypes>
      </schema>
      <drawingTemplates>
        <drawingTemplate name="Drawing Template 1">
          <template name="Default Drawing Template" parentTemplate="" type="Drawing">
            <uiProperties uiType="Basic.Default"/>
            <uiAttributes/>
          </template>
          <template name="Default Node Template" parentTemplate="" type="Node">
            <uiProperties uiType="Basic.Rectangle"/>
            <uiAttributes/>
          </template>
          <template name="Default Edge Template" parentTemplate="" type="Edge">
            <uiProperties uiType="Basic.Straight Undirected"/>
            <uiAttributes/>
          </template>
          <template name="Default Connector Template" parentTemplate="" type="Connector">
            <uiProperties uiType="Basic.Oval"/>
            <uiAttributes/>
          </template>
          <template name="Default Node Label Template" parentTemplate="" type="Node Label">
            <uiProperties uiType="Basic.Text"/>
            <uiAttributes/>
          </template>
          <template name="Default Edge Label Template" parentTemplate="" type="Edge Label">
            <uiProperties uiType="Basic.Text"/>
            <uiAttributes/>
          </template>
          <template name="Default Connector Label Template" parentTemplate="" type="Connector Label">
            <uiProperties uiType="Basic.Text"/>
            <uiAttributes/>
          </template>
          <template name="Default Edge Decoration Template" parentTemplate="" type="Edge Decoration">
            <uiProperties uiType="Basic.Symbol"/>
            <uiAttributes/>
          </template>
          <template name="Default Edge Decoration Label Template" parentTemplate="" type="Edge Decoration Label">
            <uiProperties uiType="Basic.Text"/>
            <uiAttributes/>
          </template>
          <template name="Default Swimlane Pool Template" parentTemplate="" type="Swimlane Pool">
            <uiProperties uiType="Basic.Default"/>
            <uiAttributes/>
          </template>
          <template name="Default Swimlane Template" parentTemplate="" type="Swimlane">
            <uiProperties uiType="Basic.Default"/>
            <uiAttributes/>
          </template>
        </drawingTemplate>
      </drawingTemplates>
      <domains/>
    </module>
    <defaults>
      <optionDefaults type="graphManager">
        <optionDefault name="routing:all:graphManager:fixedSize" value="false"/>
        <optionDefault name="routing:all:graphManager:fixedPosition" value="false"/>
        <optionDefault name="layout:orthogonal:graphManager:bundleOppositeMultiEdges" value="false"/>
        <optionDefault name="routing:all:graphManager:horizontalSpacingBetweenEdges" value="12.0"/>
        <optionDefault name="routing:all:graphManager:verticalSpacingBetweenEdges" value="12.0"/>
        <optionDefault name="layout:hierarchical:graphManager:compactHierarchicalLayoutEnabled" value="false"/>
        <optionDefault name="layout:orthogonal:graphManager:debugDraftSymmetricLayout" value="false"/>
        <optionDefault name="layout:all:graphManager:incrementalLayout" value="false"/>
        <optionDefault name="routing:all:graphManager:slopeEndRouting" value="true"/>
        <optionDefault name="layout:all:graphManager:drawingFitting" value="0.0"/>
        <optionDefault name="layout:all:graphManager:debugSymmetricInteger" value="0"/>
        <optionDefault name="layout:all:graphManager:debugSymmetricBoolean" value="false"/>
        <optionDefault name="layout:all:graphManager:useBendsForMultiEdges" value="true"/>
        <optionDefault name="layout:all:graphManager:aspectRatio" value="0.0"/>
        <optionDefault name="all:all:graphManager:performLabeling" value="true"/>
        <optionDefault name="routing:all:graphManager:horizontalSpacingBetweenNodes" value="20.0"/>
        <optionDefault name="routing:all:graphManager:verticalSpacingBetweenNodes" value="20.0"/>
      </optionDefaults>
      <optionDefaults type="graph">
        <optionDefault name="layout:bundle:graph:horizontalSpacingBetweenEdges" value="20.0"/>
        <optionDefault name="layout:timeline:graph:quality" value="LAYOUT_QUALITY_MEDIUM"/>
        <optionDefault name="layout:hierarchical:graph:slopeEndRouting" value="true"/>
        <optionDefault name="layout:orthogonal:graph:verticalSpacingBetweenNodes" value="20.0"/>
        <optionDefault name="layout:timeline:graph:horizontalNodeSpacing" value="20.0"/>
        <optionDefault name="layout:circular:graph:minNumberOfClusters" value="1"/>
        <optionDefault name="layout:pathTrace:graph:horizontalEdgeSpacing" value="12.0"/>
        <optionDefault name="layout:circular:graph:nodeSpacing" value="10.0"/>
        <optionDefault name="layout:hierarchical:graph:undirected" value="false"/>
        <optionDefault name="layout:orthogonal:graph:quality" value="LAYOUT_QUALITY_MEDIUM"/>
        <optionDefault name="layout:hierarchical:graph:quality" value="LAYOUT_QUALITY_MEDIUM"/>
        <optionDefault name="layout:timeline:graph:variableLevelSpacing" value="true"/>
        <optionDefault name="layout:all:graph:componentSpacing" value="50.0"/>
        <optionDefault name="layout:bundle:graph:groupEdges" value="LAYOUT_GROUP_EDGES_ALL"/>
        <optionDefault name="layout:all:graph:layoutStyle" value="LAYOUT_STYLE_HIERARCHICAL"/>
        <optionDefault name="layout:orthogonal:graph:strictIncremental" value="false"/>
        <optionDefault name="layout:orthogonal:graph:slopeEndRouting" value="true"/>
        <optionDefault name="layout:orthogonal:graph:keepNodeSizes" value="false"/>
        <optionDefault name="layout:circular:graph:maxNumberOfClusters" value="2147483647"/>
        <optionDefault name="layout:hierarchical:graph:levelAlignment" value="CENTER_ALIGNED"/>
        <optionDefault name="layout:hierarchical:graph:proportionalHorizontalSpacingBetweenNodes" value="0.0"/>
        <optionDefault name="layout:symmetric:graph:multiEdgeSpacing" value="5.0"/>
        <optionDefault name="layout:bundle:graph:verticalSpacingBetweenEdges" value="20.0"/>
        <optionDefault name="layout:orthogonal:graph:horizontalSpacingBetweenEdges" value="12.0"/>
        <optionDefault name="layout:symmetric:graph:nodeSpacing" value="10.0"/>
        <optionDefault name="layout:all:graph:detectComponents" value="true"/>
        <optionDefault name="layout:all:graph:detectDisconnectedNodes" value="true"/>
        <optionDefault name="layout:hierarchical:graph:keepNodeSizes" value="false"/>
        <optionDefault name="layout:all:graph:sortingComparator" value="SORT_NONE"/>
        <optionDefault name="layout:hierarchical:graph:constantVerticalSpacingBetweenNodes" value="28.0"/>
        <optionDefault name="layout:pathTrace:graph:verticalEdgeSpacing" value="12.0"/>
        <optionDefault name="layout:all:graph:incrementalCompaction" value="true"/>
        <optionDefault name="layout:orthogonal:graph:verticalSpacingBetweenEdges" value="12.0"/>
        <optionDefault name="layout:circular:graph:quality" value="LAYOUT_QUALITY_MEDIUM"/>
        <optionDefault name="layout:all:graph:sortingOrientation" value="SORT_COLUMN"/>
        <optionDefault name="layout:bundle:graph:identitySegmentLength" value="10"/>
        <optionDefault name="layout:hierarchical:graph:verticalSpacingBetweenEdges" value="12.0"/>
        <optionDefault name="layout:hierarchical:graph:proportionalVerticalSpacingBetweenNodes" value="0.0"/>
        <optionDefault name="layout:hierarchical:graph:orthogonalRouting" value="false"/>
        <optionDefault name="layout:orthogonal:graph:strictDrawingFitting" value="0.0"/>
        <optionDefault name="layout:pathTrace:graph:horizontalNodeSpacing" value="20.0"/>
        <optionDefault name="layout:timeline:graph:verticalEdgeSpacing" value="12.0"/>
        <optionDefault name="layout:bundle:graph:horizontalSpacingBetweenNodes" value="20.0"/>
        <optionDefault name="layout:all:graph:disconnectedNodeSpacing" value="20.0"/>
        <optionDefault name="layout:all:graph:fixed" value="false"/>
        <optionDefault name="layout:symmetric:graph:quality" value="LAYOUT_QUALITY_MEDIUM"/>
        <optionDefault name="layout:hierarchical:graph:backwardEdgeMode" value="BACKWARD_EDGE_MODE_MINIMIZE"/>
        <optionDefault name="layout:hierarchical:graph:edgeSpacing" value="12.0"/>
        <optionDefault name="layout:timeline:graph:horizontalEdgeSpacing" value="12.0"/>
        <optionDefault name="layout:orthogonal:graph:useCombLabeling" value="false"/>
        <optionDefault name="layout:hierarchical:graph:variableLevelSpacing" value="true"/>
        <optionDefault name="layout:all:graph:sortDisconnectedNodes" value="false"/>
        <optionDefault name="layout:symmetric:graph:randomSeed" value="1"/>
        <optionDefault name="layout:hierarchical:graph:levelDirection" value="DIRECTION_TOP_TO_BOTTOM"/>
        <optionDefault name="layout:circular:graph:clusterLayoutStyle" value="LAYOUT_STYLE_SYMMETRIC"/>
        <optionDefault name="layout:circular:graph:clusterSizeFactor" value="1.0"/>
        <optionDefault name="layout:circular:graph:clusterSpacing" value="50.0"/>
        <optionDefault name="layout:pathTrace:graph:verticalNodeSpacing" value="20.0"/>
        <optionDefault name="layout:timeline:graph:baselineEnabled" value="false"/>
        <optionDefault name="layout:bundle:graph:verticalSpacingBetweenNodes" value="20.0"/>
        <optionDefault name="layout:orthogonal:graph:horizontalSpacingBetweenNodes" value="20.0"/>
        <optionDefault name="layout:hierarchical:graph:constantHorizontalSpacingBetweenNodes" value="28.0"/>
        <optionDefault name="layout:circular:graph:multiEdgeSpacing" value="5.0"/>
        <optionDefault name="layout:timeline:graph:verticalNodeSpacing" value="20.0"/>
        <optionDefault name="layout:circular:graph:balance" value="1.0"/>
        <optionDefault name="layout:hierarchical:graph:horizontalSpacingBetweenEdges" value="12.0"/>
      </optionDefaults>
      <optionDefaults type="edgeDecoration">
        <optionDefault name="layout:all:edgeDecoration:relativeDistance" value="-1.0"/>
      </optionDefaults>
      <optionDefaults type="connector">
        <optionDefault name="all:all:connector:resizable" value="true"/>
        <optionDefault name="all:all:connector:movable" value="true"/>
        <optionDefault name="all:all:connector:orientation" value="CONNECTOR_ORIENTATION_ON_BORDER"/>
        <optionDefault name="all:all:connector:side" value="ANY_SIDE"/>
      </optionDefaults>
      <optionDefaults type="edge">
        <optionDefault name="all:all:edge:width" value="1.0"/>
        <optionDefault name="all:all:edge:targetAttachmentSide" value="ATTACHMENT_SIDE_ANY"/>
        <optionDefault name="layout:all:edge:embedded" value="false"/>
        <optionDefault name="all:all:edge:sourceArrowLength" value="0.0"/>
        <optionDefault name="all:all:edge:targetArrowLength" value="0.0"/>
        <optionDefault name="layout:oneLineDiagram:edge:sourceArrowLength" value="0"/>
        <optionDefault name="all:all:edge:targetArrowWidth" value="0.0"/>
        <optionDefault name="layout:oneLineDiagram:edge:targetArrowWidth" value="0"/>
        <optionDefault name="all:all:edge:sourceAttachmentSide" value="ATTACHMENT_SIDE_ANY"/>
        <optionDefault name="layout:oneLineDiagram:edge:targetArrowLength" value="0"/>
        <optionDefault name="layout:circular:edge:cost" value="1"/>
        <optionDefault name="all:all:edge:sourceArrowWidth" value="0.0"/>
        <optionDefault name="layout:oneLineDiagram:edge:sourceArrowWidth" value="0"/>
        <optionDefault name="layout:hierarchical:edge:type" value="EDGE_TYPE_DIRECTED"/>
        <optionDefault name="layout:symmetric:edge:desiredLengthMultiplier" value="1.0"/>
      </optionDefaults>
      <optionDefaults type="none">
        <optionDefault name="all:all:none:operation" value="OPERATION_LAYOUT"/>
        <optionDefault name="layout:oneLineDiagram:none:useLegacyMethod" value="false"/>
      </optionDefaults>
      <optionDefaults type="connectorLabel">
        <optionDefault name="labeling:all:connectorLabel:fixed" value="false"/>
        <optionDefault name="labeling:all:connectorLabel:orientation" value="CONNECTOR_LABEL_ORIENTATION_INSIDE_PARENT"/>
      </optionDefaults>
      <optionDefaults type="edgeDecorationLabel">
        <optionDefault name="labeling:all:edgeDecorationLabel:region" value="EDGE_DECORATION_LABEL_REGION_ANY"/>
      </optionDefaults>
      <optionDefaults type="edgeLabel">
        <optionDefault name="labeling:all:edgeLabel:association" value="EDGE_LABEL_ASSOCIATION_CENTER"/>
        <optionDefault name="labeling:all:edgeLabel:region" value="EDGE_LABEL_REGION_ANY"/>
        <optionDefault name="labeling:all:edgeLabel:fixed" value="false"/>
      </optionDefaults>
      <optionDefaults type="nodeLabel">
        <optionDefault name="labeling:all:nodeLabel:location" value="NODE_LABEL_LOCATION_CENTER"/>
        <optionDefault name="labeling:all:nodeLabel:fixed" value="false"/>
        <optionDefault name="labeling:all:nodeLabel:orientation" value="NODE_LABEL_ORIENTATION_OUTSIDE"/>
        <optionDefault name="labeling:all:nodeLabel:region" value="NODE_LABEL_REGION_ANY"/>
      </optionDefaults>
      <optionDefaults type="node">
        <optionDefault name="all:all:node:fitNestedGraph" value="true"/>
        <optionDefault name="layout:oneLineDiagram:node:isBus" value="false"/>
        <optionDefault name="all:all:node:minimalTightExpandedHeight" value="0.0"/>
        <optionDefault name="layout:all:node:embedded" value="true"/>
        <optionDefault name="layout:oneLineDiagram:node:drawingSide" value="UNDEFINED_SIDE"/>
        <optionDefault name="routing:all:node:fixedSize" value="false"/>
        <optionDefault name="all:all:node:minimalTightExpandedWidth" value="0.0"/>
        <optionDefault name="layout:hierarchical:node:decisionNode" value="false"/>
        <optionDefault name="layout:oneLineDiagram:node:isSwitch" value="false"/>
        <optionDefault name="layout:timeline:node:type" value="NODE_TYPE_UNDEFINED"/>
        <optionDefault name="all:all:node:transientShape" value="false"/>
        <optionDefault name="layout:circular:node:weight" value="1"/>
        <optionDefault name="routing:all:node:fixedPosition" value="false"/>
        <optionDefault name="all:all:node:attachmentSide" value="ATTACHMENT_SIDE_ANY"/>
      </optionDefaults>
    </defaults>
  </project>
</TSX>
